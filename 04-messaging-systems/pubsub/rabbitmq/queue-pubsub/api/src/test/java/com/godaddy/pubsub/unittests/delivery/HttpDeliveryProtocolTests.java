package com.godaddy.pubsub.unittests.delivery;


import com.godaddy.pubsub.componentTests.server.SelfHostServer;
import com.godaddy.pubsub.delivery.HttpDeliveryProtocol;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.messages.MessageId;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriberMessageDeliveryUri;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.godaddy.pubsub.resources.api.v1.DeliveryResource;
import lombok.Cleanup;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpDeliveryProtocolTests {

    final int DISCARD_PORT = 9;

    @Test
    public void delivery_success() throws Exception {
        @Cleanup SelfHostServer server = new SelfHostServer(Collections.emptyList());
        server.start();

        String subscriberUri = UriBuilder.fromUri(server.getBaseUri())
                .replacePath(DeliveryResource.class.getAnnotation(Path.class).value())
                .toString();

        Subscription sub = buildSubscription(subscriberUri);

        boolean accepted = new HttpDeliveryProtocol().deliver(sub, buildMessage());

        assertThat(accepted).isTrue();
    }

    @Test
    public void delivery_failure_by_rejection() throws Exception {
        @Cleanup SelfHostServer server = new SelfHostServer(Collections.emptyList());
        server.start();

        String subscriberUri = UriBuilder.fromUri(server.getBaseUri())
                .replacePath("definitely-not-the-right-path")
                .toString();

        Subscription sub = buildSubscription(subscriberUri);

        boolean accepted = new HttpDeliveryProtocol().deliver(sub, buildMessage());

        assertThat(accepted).isFalse();
    }

    @Test
    public void delivery_failure_by_no_response() throws Exception {
        Subscription sub = buildSubscription("http://localhost:" + DISCARD_PORT);

        boolean accepted = new HttpDeliveryProtocol().deliver(sub, buildMessage());

        assertThat(accepted).isFalse();
    }

    private Subscription buildSubscription(String uri) {
        return Subscription.builder()
                .subscriptionId(SubscriptionId.random())
                .topicId(TopicId.valueOf("unit_test"))
                .messageDeliveryUri(SubscriberMessageDeliveryUri.valueOf(uri))
                .build();
    }

    private Message buildMessage() {
        return Message.builder()
                .messageId(MessageId.valueOf("4321"))
                .requestId("1234")
                .timestamp(DateTime.now())
                .data("round and round".getBytes())
                .build();
    }
}
