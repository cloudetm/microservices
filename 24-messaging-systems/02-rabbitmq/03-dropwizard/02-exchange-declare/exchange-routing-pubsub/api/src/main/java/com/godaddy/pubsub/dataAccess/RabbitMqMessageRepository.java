package com.godaddy.pubsub.dataAccess;

import com.godaddy.pubsub.Consts;
import com.godaddy.pubsub.dataAccess.interfaces.MessageDeliveryListener;
import com.godaddy.pubsub.dataAccess.interfaces.MessageRepository;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.google.inject.Singleton;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

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

    // https://www.rabbitmq.com/tutorials/tutorial-four-java.html
    @Override
    public void publish(Topic topic, Message msg) throws IOException {

        Connection connection = null;
        Channel channel = null;

        try{
            ConnectionFactory factory = new ConnectionFactory();
            // setHost(String host)
            factory.setHost(Consts.HOST);
            connection = factory.newConnection();
            channel = connection.createChannel();

            // exchangeDeclare(String exchange, String type)
            channel.exchangeDeclare(Consts.EXCHANGE_NAME, "direct");

            String topicId = topic.getTopicId().toString();
            String message = new String(msg.getData());

            // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
            channel.basicPublish(Consts.EXCHANGE_NAME, topicId, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + topicId + "':'" + message + "'");
        }
        finally{
            channel.close();
            connection.close();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void cleanup() {

    }
}
