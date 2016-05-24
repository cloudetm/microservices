package com.godaddy.pubsub.queues.rabbitmq;

import com.godaddy.pubsub.dataAccess.interfaces.MessageMiddlewareDriver;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.queues.interfaces.MessageDeliveryListener;
import com.godaddy.pubsub.queues.rabbitmq.interfaces.RabbitMqPubsubProvider;
import com.rabbitmq.client.Channel;
import io.paradoxical.rabbitmq.ChannelInitializer;
import io.paradoxical.rabbitmq.Exchange;
import io.paradoxical.rabbitmq.PublisherProviderImpl;
import io.paradoxical.rabbitmq.connectionManagment.ChannelProvider;
import io.paradoxical.rabbitmq.connectionManagment.ExchangeUtils;
import io.paradoxical.rabbitmq.queues.EventBase;
import io.paradoxical.rabbitmq.queues.QueuePublisher;

import javax.inject.Inject;
import java.io.IOException;

public class RabbitMqMessageMiddlewareDriver implements MessageMiddlewareDriver {

    private ChannelProvider channelProvider;
    private RabbitMqPubsubProvider rabbitMqPubsubProvider;

    @Inject
    public RabbitMqMessageMiddlewareDriver(
            final ChannelProvider channelProvider,
            final RabbitMqPubsubProvider rabbitMqPubsubProvider){

        this.channelProvider = channelProvider;
        this.rabbitMqPubsubProvider = rabbitMqPubsubProvider;
    }

    @Override
    public void createTopic(Topic topic) {
        // create exchange
        Channel channel = channelProvider.getChannel();
        Exchange publishExchange = rabbitMqPubsubProvider.getPublishExchange(topic);

        try {
            ExchangeUtils.declare(channel, publishExchange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSubscription(Topic topic, Subscription sub) {
        // create queue
        try{
            Channel channel = channelProvider.getChannel();
            ChannelInitializer.initializeExchange(
                    channel,
                    rabbitMqPubsubProvider.getPublishExchange(topic),
                    rabbitMqPubsubProvider.getQueue(sub.getSubscriptionId(), sub.getTopicId()));
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void publish(Topic topic, Message msg) {
        try{
            QueuePublisher<EventBase> publisher = new PublisherProviderImpl<>(channelProvider)
                    .forExchange(rabbitMqPubsubProvider.getPublishExchange(topic), topic.getTopicId().get());
            publisher.publish(new RabbitMqMessage(msg));
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setListener(Subscription sub, MessageDeliveryListener listener) {

    }
}
