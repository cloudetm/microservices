package com.company.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/*
The producer program, which emits log messages. We publish messages to our logs exchange instead of the nameless one.
We need to supply a routingKey when sending, but its value is ignored for fanout exchanges.
 */
public class App
{
    private static final String EXCHANGE_NAME = "logs";
    public static void main( String[] args ) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchangeDeclare(String exchange, String type)
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = getMessage(args);

        // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

    }
    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "info: Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
