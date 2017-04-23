package com.godaddy.pubsub.queues.interfaces;

import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;

public interface MessageMiddlewareDriver {
    void createTopic(final Topic topic);
    void createSubscription(final Topic topic, final Subscription sub);
    void publish(final Topic topic, final Message msg);
}
