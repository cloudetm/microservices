package com.godaddy.pubsub.dataAccess.interfaces;

import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;

public interface SubscriptionsRepository {
    ImmutableList<Subscription> getAllSubscriptions();
    ImmutableList<Subscription> getTopicSubscriptions(TopicId topicId);
    Subscription getSubscription(final SubscriptionId subscriptionId);
    void createSubscription(final Subscription subscription);
    void deleteSubscription(final SubscriptionId subscriptionId);
}
