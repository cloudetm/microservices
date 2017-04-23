package com.godaddy.pubsub.queues.interfaces;

import com.godaddy.pubsub.pub.model.subscriptions.Subscription;

public interface MessageProcessorController {
    void start();
    void stop() throws Exception;
    void addSubscription(Subscription subscription);
}
