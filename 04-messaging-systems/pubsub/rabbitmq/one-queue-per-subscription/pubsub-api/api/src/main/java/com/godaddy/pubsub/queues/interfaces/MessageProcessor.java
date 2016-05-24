package com.godaddy.pubsub.queues.interfaces;

import com.godaddy.pubsub.pub.model.subscriptions.Subscription;

public interface MessageProcessor {
    void start();
    void stop() throws Exception;

    interface Factory{
        MessageProcessor create(Subscription subscription);
    }
}
