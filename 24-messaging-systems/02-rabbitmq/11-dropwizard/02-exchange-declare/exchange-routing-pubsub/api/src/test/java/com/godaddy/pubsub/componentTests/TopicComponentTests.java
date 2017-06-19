package com.godaddy.pubsub.componentTests;

import com.godaddy.pubsub.Consts;
import com.godaddy.pubsub.pub.client.ServiceClient;
import com.godaddy.pubsub.pub.model.messages.MessageTicket;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriberMessageDeliveryUri;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.godaddy.pubsub.pub.model.topics.TopicNameDescriptor;
import com.google.common.collect.ImmutableList;
import com.rabbitmq.client.*;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import org.junit.Test;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TopicComponentTests extends TestBase {
    private static final Topic TOPIC = Topic.builder()
            .nameDescriptor(
                    TopicNameDescriptor.builder()
                            .team("test team")
                            .project("test project")
                            .entity("test entity")
                            .eventType("test eventtype")
                            .majorVersion(1)
                            .build())
            .description("test description")
            .build();


    @Test
    public void get_topics() throws IOException {

        Response<Topic> createTopicResponse = createTopic(TOPIC);

        Topic topic2 = Topic.builder()
                .nameDescriptor(
                        TopicNameDescriptor.builder()
                                .team("team 2")
                                .project("project 2")
                                .entity("entity 2")
                                .eventType("eventtype 2")
                                .majorVersion(1)
                                .build())
                .description("description 2")
                .build();
        createTopic(topic2);
        List<Topic> expectedTopics = Arrays.asList(TOPIC, topic2);

        final ServiceClient client = createServiceClient();
        Response<ImmutableList<Topic>> allTopicsResponse = client.getTopics().execute();

        ImmutableList<Topic> topics = allTopicsResponse.body();
        Topic actualTopic = topics.get(topics.size() - 1);
        Topic expectedTopic = TOPIC.toBuilder()
                .topicId(createTopicResponse.body().getTopicId())
                .build();

        assertThat(allTopicsResponse.code()).isEqualTo(200);
        assertThat(topics).hasSameSizeAs(expectedTopics);
        assertThat(actualTopic).isEqualToComparingFieldByField(expectedTopic);
    }

    private Response<Topic> createTopic(Topic topic) throws IOException {

        final ServiceClient client = createServiceClient();
        Call<Topic> topicCreate = client.createTopic(topic);
        return topicCreate.execute();
    }

    @Test
    public void get_topic_subscriptions() throws IOException {

        final ServiceClient client = createServiceClient();

        Response<Topic> createTopicResponse = createTopic(TOPIC);

        List<Subscription> expectedSubscriptions = new ArrayList<>();
        IntStream.range(0, 2).forEach(i -> {
            Subscription subscription = Subscription.builder()
                    .description("test description " + i)
                    .topicId(createTopicResponse.body().getTopicId())
                    .messageDeliveryUri(SubscriberMessageDeliveryUri.valueOf("http://www.test.com"))
                    .build();
            try {
                client.createSubscription(subscription).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            expectedSubscriptions.add(subscription);
        });

        Response<ImmutableList<Subscription>> topicSubscriptionsResponse = client
                .getTopicSubscriptions(createTopicResponse.body().getTopicId()).execute();

        assertThat(topicSubscriptionsResponse.code()).isEqualTo(200);
        assertThat(topicSubscriptionsResponse.body()).hasSameSizeAs(expectedSubscriptions);
        assertThat(topicSubscriptionsResponse.body()).extracting(sub -> sub.getDescription())
                .contains("test description 0", "test description 1");
    }

    @Test
    public void create_topic() throws IOException {

        Response<Topic> createTopicResponse = createTopic(TOPIC);

        assertThat(createTopicResponse.code()).isEqualTo(201);
        assertThat(createTopicResponse.body().getDescription()).isEqualTo(TOPIC.getDescription());
    }

    @Test
    public void create_topics_with_null_topicnamedescriptor() throws IOException {

        Topic topic = Topic.builder()
                .nameDescriptor(null)
                .description(TOPIC.getDescription())
                .build();
        Response<Topic> createTopicResponse = createTopic(topic);

        assertThat(createTopicResponse.code()).isEqualTo(422);
    }

    @Test
    public void publish_message() throws IOException {

        final ServiceClient client = createServiceClient();

        Response<Topic> createTopicResponse = createTopic(TOPIC);

        // create a temporary queue for consumer to receive message
        // https://www.rabbitmq.com/tutorials/tutorial-four-java.html
        receive_message_by_using_temporary_queue_has_routing(createTopicResponse.body().getTopicId());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Response<MessageTicket> publishMessageResponse = client.publishMessage(
                createTopicResponse.body().getTopicId(),
                "my-request-id",
                RequestBody.create(MediaType.parse("text/plain"), "test")
        ).execute();

        assertThat(publishMessageResponse.code()).isEqualTo(200);
        assertThat(publishMessageResponse.body().getMessageId().toString()).isNotEmpty();
    }

    private void receive_message_by_using_temporary_queue_has_routing(TopicId topicId) throws IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Consts.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchangeDeclare(String exchange, String type)
        channel.exchangeDeclare(Consts.EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        // queueBind(String queue, String exchange, String routingKey)
        channel.queueBind(queueName, Consts.EXCHANGE_NAME, topicId.toString());

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
