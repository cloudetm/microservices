package com.company.app;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

        // permanent queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(
                    String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
