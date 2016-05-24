package com.godaddy.pubsub.queues.rabbitmq;

import com.godaddy.pubsub.delivery.interfaces.DeliveryProtocol;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.queues.interfaces.Listener;
import com.godaddy.pubsub.queues.interfaces.MessageProcessor;
import com.google.inject.assistedinject.Assisted;

import java.util.function.Consumer;

public class DefaultMessageProcessor implements MessageProcessor {
    private final Listener listener;

    public DefaultMessageProcessor(final DeliveryProtocol deliveryProtocol,
                                   final Listener.Factory listenerFactory,
                                   @Assisted final Subscription subscription) {
        this.listener = listenerFactory.create(subscription, createMessageConsumer(subscription, deliveryProtocol));
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() throws Exception {

    }

    private static Consumer<Message> createMessageConsumer(final Subscription subscription,
                                                           final DeliveryProtocol deliveryProtocol){
        return message -> deliveryProtocol.deliver(subscription, message);
    }
}
