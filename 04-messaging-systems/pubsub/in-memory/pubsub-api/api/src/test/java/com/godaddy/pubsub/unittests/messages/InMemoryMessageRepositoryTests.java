package com.godaddy.pubsub.unittests.messages;

import com.godaddy.pubsub.dataAccess.InMemoryMessageRepository;
import com.godaddy.pubsub.dataAccess.interfaces.MessageDeliveryListener;
import com.godaddy.pubsub.dataAccess.interfaces.MessageRepository;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.messages.MessageId;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class InMemoryMessageRepositoryTests {

    @Test
    public void delivery_thread_delivers_queued_messages() throws InterruptedException {
        InMemoryMessageRepository repo = new InMemoryMessageRepository();
        Topic topic = createTopic("unit_test", repo);
        Subscription sub = createSubscription(topic, repo);

        List<Message> received = Collections.synchronizedList(new LinkedList<>());
        Semaphore sem = new Semaphore(0);

        repo.setListener(sub, new MessageDeliveryListener() {
            @Override
            public boolean deliverMessage(SubscriptionId subId, Message msg) {
                received.add(msg);
                sem.release();
                return true;
            }
        });

        String text = "I hope this works";
        repo.publish(topic, buildMessage(text));

        repo.init();
        sem.tryAcquire(3000, TimeUnit.MILLISECONDS);
        repo.cleanup();

        assertThat(repo.isDeliveryThreadAlive()).isFalse();

        assertThat(received)
                .hasSize(1)
                .extracting(msg -> new String(msg.getData()))
                .containsExactly(text);
    }

    @Test
    public void new_listeners_receive_queued_messages() {
        InMemoryMessageRepository repo = new InMemoryMessageRepository();
        Topic topic = createTopic("unit_test", repo);
        Subscription sub = createSubscription(topic, repo);

        String text1 = "I'll wait";
        repo.publish(topic, buildMessage(text1));

        String text2 = "as long as it takes";
        repo.publish(topic, buildMessage(text2));

        List<Message> received = listen(sub, repo);
        repo.deliverMessages(false);

        assertThat(received)
                .hasSize(2)
                .extracting(msg -> new String(msg.getData()))
                .containsExactly(text1, text2);
    }

    @Test
    public void topics_are_independent() {
        InMemoryMessageRepository repo = new InMemoryMessageRepository();

        Topic topicA = createTopic("apples", repo);
        Subscription subA = createSubscription(topicA, repo);
        List<Message> receivedA = listen(subA, repo);

        Topic topicB = createTopic("oranges", repo);
        Subscription subB = createSubscription(topicB, repo);

        List<Message> receivedB = listen(subB, repo);

        String textA = "apples are convenient";
        repo.publish(topicA, buildMessage(textA));

        String textB = "oranges are too much work";
        repo.publish(topicB, buildMessage(textB));

        repo.deliverMessages(false);

        assertThat(receivedA)
                .hasSize(1)
                .extracting(msg -> new String(msg.getData()))
                .containsExactly(textA);

        assertThat(receivedB)
                .hasSize(1)
                .extracting(msg -> new String(msg.getData()))
                .containsExactly(textB);
    }


    @Test
    public void subscribers_only_receive_messages_published_going_forward() {
        InMemoryMessageRepository repo = new InMemoryMessageRepository();
        Topic topic = createTopic("unit_test", repo);

        String text0 = "lost forever";
        repo.publish(topic, buildMessage(text0));

        Subscription subA = createSubscription(topic, repo);
        List<Message> receivedA = listen(subA, repo);

        String text1 = "message1";
        repo.publish(topic, buildMessage(text1));

        Subscription subB = createSubscription(topic, repo);
        List<Message> receivedB = listen(subB, repo);

        String text2 = "message-two";
        repo.publish(topic, buildMessage(text2));

        repo.deliverMessages(false);

        // A subscribed early and gets both messages
        assertThat(receivedA)
                .hasSize(2)
                .extracting(msg -> new String(msg.getData()))
                .containsExactly(text1, text2);

        // B subscribed too late to receive message1
        assertThat(receivedB)
                .hasSize(1)
                .extracting(msg -> new String(msg.getData()))
                .containsExactly(text2);
    }

    @Test
    public void delete_topic() {
        InMemoryMessageRepository repo = new InMemoryMessageRepository();
        Topic topic = createTopic("unit_test", repo);

        repo.publish(topic, buildMessage("unused"));
        repo.deleteTopic(topic);
        Throwable thrown = catchThrowable(() -> repo.publish(topic, buildMessage("unused")));

        assertThat(thrown).hasMessageContaining("exist");
    }

    @Test
    public void delete_subscription() {
        InMemoryMessageRepository repo = new InMemoryMessageRepository();
        Topic topic = createTopic("unit_test", repo);
        Subscription sub = createSubscription(topic, repo);

        listen(sub, repo);
        repo.deleteSubscription(sub);
        Throwable thrown = catchThrowable(() -> listen(sub, repo));

        assertThat(thrown).hasMessageContaining("exist");
    }

    @Test
    public void removed_listener_stops_getting_messages() {
        InMemoryMessageRepository repo = new InMemoryMessageRepository();
        Topic topic = createTopic("unit_test", repo);
        Subscription sub = createSubscription(topic, repo);

        List<Message> received = listen(sub, repo);
        repo.publish(topic, buildMessage("unused"));
        repo.deliverMessages(false);
        assertThat(received).hasSize(1);

        repo.removeListener(sub);

        received.clear();
        repo.publish(topic, buildMessage("unused"));
        repo.deliverMessages(false);
        assertThat(received).hasSize(0);
    }

    @Test
    public void rejected_deliveries_are_retried() {
        InMemoryMessageRepository repo = new InMemoryMessageRepository();
        Topic topic = createTopic("unit_test", repo);
        Subscription sub = createSubscription(topic, repo);

        final int DESIRED_NUM_DELIVERIES = 3;
        List<Message> received = new LinkedList<>();

        repo.setListener(sub, new MessageDeliveryListener() {
            @Override
            public boolean deliverMessage(SubscriptionId subId, Message msg) {
                received.add(msg);
                return received.size() >= DESIRED_NUM_DELIVERIES;
            }
        });

        repo.publish(topic, buildMessage("unused"));
        repo.deliverMessages(false);

        assertThat(received).hasSize(DESIRED_NUM_DELIVERIES);

    }

    private Topic createTopic(String topicName, MessageRepository repo) {
        Topic topic  = new Topic(null, TopicId.valueOf(topicName), "" );
        repo.createTopic(topic);
        return topic;
    }

    private Subscription createSubscription(Topic topic, MessageRepository repo) {
        Subscription sub = new Subscription(SubscriptionId.random(), topic.getTopicId(), "", null);
        repo.createSubscription(sub);
        return sub;
    }

    private Message buildMessage(String text) {
        return Message.builder()
                .messageId(MessageId.valueOf("4321"))
                .requestId("1234")
                .timestamp(DateTime.now())
                .data(text.getBytes())
                .build();
    }

    private List<Message> listen(Subscription sub, MessageRepository repo){
        List<Message> received = new LinkedList<>();
        repo.setListener(sub, new MessageDeliveryListener() {
            @Override
            public boolean deliverMessage(SubscriptionId subId, Message msg) {
                received.add(msg);
                return true;
            }
        });
        return received;
    }
}
