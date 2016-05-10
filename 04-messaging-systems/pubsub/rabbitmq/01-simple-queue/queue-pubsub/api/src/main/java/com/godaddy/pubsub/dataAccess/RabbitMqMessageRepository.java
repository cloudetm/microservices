package com.godaddy.pubsub.dataAccess;

import com.godaddy.pubsub.dataAccess.interfaces.MessageDeliveryListener;
import com.godaddy.pubsub.dataAccess.interfaces.MessageRepository;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.google.inject.Singleton;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.paradoxical.rabbitmq.Exchange;
import io.paradoxical.rabbitmq.PublisherProviderImpl;
import io.paradoxical.rabbitmq.connectionManagment.ChannelProvider;
import io.paradoxical.rabbitmq.connectionManagment.Host;
import io.paradoxical.rabbitmq.connectionManagment.SimpleChannelProvider;
import io.paradoxical.rabbitmq.queues.EventBase;
import io.paradoxical.rabbitmq.queues.QueuePublisher;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Singleton
public class RabbitMqMessageRepository implements MessageRepository {

    @Override
    public void createSubscription(Subscription sub) {

    }

    @Override
    public void createTopic(Topic topic) {

    }

    @Override
    public void deleteSubscription(Subscription sub) {

    }

    @Override
    public void deleteTopic(Topic topic) {

    }

    @Override
    public void setListener(Subscription sub, MessageDeliveryListener listener) {

    }

    @Override
    public void removeListener(Subscription sub) {

    }

    // https://www.rabbitmq.com/tutorials/tutorial-one-java.html
    @Override
    public void publish(Topic topic, Message msg) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String topicId = topic.getTopicId().toString();

        channel.queueDeclare(topicId, false, false, false, null);

        String message = new String(msg.getData());
        channel.basicPublish("", topicId, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }

    private void old(Topic topic, Message msg) throws IOException {
    }

    @Override
    public void init() {

    }

    @Override
    public void cleanup() {

    }
}
