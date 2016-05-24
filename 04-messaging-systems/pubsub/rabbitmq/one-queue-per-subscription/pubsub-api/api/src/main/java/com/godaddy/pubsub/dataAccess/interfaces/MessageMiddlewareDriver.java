package com.godaddy.pubsub.dataAccess.interfaces;

import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.queues.interfaces.MessageDeliveryListener;

public interface MessageMiddlewareDriver {
    void createTopic(final Topic topic);
    void createSubscription(final Topic topic, final Subscription sub);
    void publish(final Topic topic, final Message msg);
    void setListener(final Subscription sub, final MessageDeliveryListener listener);
}
