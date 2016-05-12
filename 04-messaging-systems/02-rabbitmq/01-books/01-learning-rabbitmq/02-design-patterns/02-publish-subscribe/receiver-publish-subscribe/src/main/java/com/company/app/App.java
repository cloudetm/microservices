package com.company.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class App
{
    private final static String EXCHANGE_NAME = "pubsub_exchange";
    public static void main( String[] args ) throws IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.queueDeclare("pubsub_queue", false, false, false, null);
        channel.queueBind("pubsub_queue", EXCHANGE_NAME, "");

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume("pubsub_queue", true, consumer);
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        String message = new String(delivery.getBody());
        System.out.println(message);

        channel.close();
        connection.close();
    }
}
