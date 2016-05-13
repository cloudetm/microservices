package com.company.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

class Consts{
    public static final String USERNAME = "pubsub";
    public static final String PASSWORD = "pubsub";
    public static final String VHOST = "pubsub";
    public static final String EXCHANGE_NAME = "direct_exchange";
    public static final String QUEUE_NAME = "queue_1";
    public static final String HOST = "localhost";
    public static final String ROUTING_KEY = "routing_key_1";
}
public class App
{
    public static void main( String[] args ) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(Consts.USERNAME);
        factory.setPassword(Consts.PASSWORD);
        factory.setVirtualHost(Consts.VHOST);
        factory.setHost(Consts.HOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(Consts.EXCHANGE_NAME, "direct", true);
        channel.queueDeclare(Consts.QUEUE_NAME, true, false, false, null);
        channel.queueBind(Consts.QUEUE_NAME, Consts.EXCHANGE_NAME, Consts.ROUTING_KEY);

        byte[] messageBodyBytes = "Hello, world!".getBytes();
        channel.basicPublish(Consts.EXCHANGE_NAME, Consts.ROUTING_KEY, null, messageBodyBytes);

        channel.close();
        connection.close();
    }
}
