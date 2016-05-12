package com.company.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class App
{
    private final static String EXCHANGE_NAME = "pubsub_exchange";
    private static final String EXCHANGE_TYPE = "direct";
    private static final String QUEUE_NAME = "pubsub_queue";

    public static void main( String[] args ) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchangeDeclare(String exchange, String type)
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

        // permanent queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        channel.basicPublish(EXCHANGE_NAME, "", null, "my message".getBytes());

        channel.close();
        connection.close();
    }
}
