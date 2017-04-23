package com.godaddy.pubsub.queues;

import com.godaddy.pubsub.dataAccess.interfaces.TopicsRepository;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.queues.interfaces.Listener;
import com.godaddy.pubsub.queues.rabbitmq.RabbitMqMessage;
import com.godaddy.pubsub.queues.rabbitmq.interfaces.RabbitMqPubsubProvider;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import io.paradoxical.rabbitmq.ListenerOptions;
import io.paradoxical.rabbitmq.QueueConfiguration;
import io.paradoxical.rabbitmq.connectionManagment.ChannelProvider;
import io.paradoxical.rabbitmq.queues.QueueListenerSync;
import io.paradoxical.rabbitmq.results.MessageResult;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.function.Consumer;

public class RabbitMqMessageListener extends QueueListenerSync<RabbitMqMessage> implements Listener {

    private final Consumer<Message> messageConsumer;

    @Inject
    public RabbitMqMessageListener(
            final ChannelProvider channelProvider,
            final RabbitMqPubsubProvider rabbitMqPubsubProvider,
            final ListenerOptions options,
            final TopicsRepository topicsRepository,
            @Assisted final Subscription subscription,
            @Assisted final Consumer<Message> messageConsumer) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        super(channelProvider,
                getQueueConfiguration(rabbitMqPubsubProvider, topicsRepository, subscription),
                RabbitMqMessage.class,
                options);
        this.messageConsumer = messageConsumer;
    }

    @Override
    public MessageResult onMessage(RabbitMqMessage rabbitMqMessage) {
        Message message = rabbitMqMessage.getMessage();
        messageConsumer.accept(message);
        return MessageResult.Ack;
    }

    private static QueueConfiguration getQueueConfiguration(
            final RabbitMqPubsubProvider rabbitMqPubsubProvider,
            final TopicsRepository topicsRepository,
            final Subscription subscription){
        final Topic topic = topicsRepository.getTopic(subscription.getTopicId());
        return new QueueConfiguration(rabbitMqPubsubProvider.getPublishExchange(topic),
                rabbitMqPubsubProvider.getQueue(subscription.getSubscriptionId(), subscription.getTopicId()));
    }
}
