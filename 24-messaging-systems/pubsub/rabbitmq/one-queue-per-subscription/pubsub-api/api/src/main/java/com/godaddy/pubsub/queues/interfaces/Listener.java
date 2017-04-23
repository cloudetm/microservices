package com.godaddy.pubsub.queues.interfaces;

import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;

import java.util.function.Consumer;

public interface Listener {
    void start();

    void stop() throws Exception;

    interface Factory{
        Listener create(Subscription subscription, Consumer<Message> messageConsumer);
    }
}
