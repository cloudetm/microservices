package com.godaddy.pubsub.configurations;

import lombok.Data;

import java.net.URI;

@Data
public class RabbitMqConfiguration {
    private URI rmqUri;

    private String username;

    private String password;

    private String vhost;
}
