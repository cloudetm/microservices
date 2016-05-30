package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.ServiceConfiguration;
import com.godaddy.pubsub.configurations.RabbitMqConfiguration;
import com.godaddy.pubsub.queues.interfaces.MessageMiddlewareDriver;
import com.godaddy.pubsub.queues.interfaces.ShardStrategy;
import com.godaddy.pubsub.queues.rabbitmq.DefaultRabbitMqPubsubProvider;
import com.godaddy.pubsub.queues.rabbitmq.RabbitMqConfigShardStrategy;
import com.godaddy.pubsub.queues.rabbitmq.RabbitMqMessageMiddlewareDriver;
import com.godaddy.pubsub.queues.rabbitmq.interfaces.RabbitMqPubsubProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.netflix.governator.guice.lazy.LazySingleton;
import com.rabbitmq.client.Address;
import io.paradoxical.rabbitmq.connectionManagment.ChannelProvider;
import io.paradoxical.rabbitmq.connectionManagment.Host;
import io.paradoxical.rabbitmq.connectionManagment.SimpleChannelProvider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class RabbitMqMiddlewareModule extends AbstractModule {

    @LazySingleton
    @Provides
    public ChannelProvider channelProvider(final ServiceConfiguration configuration)
            throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {
        final RabbitMqConfiguration rabbitMqConfiguration = configuration.getRabbitMqConfiguration();

        final Host host = new Host(rabbitMqConfiguration.getRmqUri());

        return new SimpleChannelProvider(
                rabbitMqConfiguration.getUsername(),
                rabbitMqConfiguration.getPassword(),
                rabbitMqConfiguration.getVhost(),
                new Address[]{new Address(host.getUri().getHost())});
    }

    @Override
    protected void configure() {
        bind(MessageMiddlewareDriver.class).to(RabbitMqMessageMiddlewareDriver.class);
        bind(ShardStrategy.class).to(RabbitMqConfigShardStrategy.class);
        bind(RabbitMqPubsubProvider.class).to(DefaultRabbitMqPubsubProvider.class);
    }
}
