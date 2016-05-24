package com.godaddy.pubsub.dataAccess;

import com.godaddy.pubsub.dataAccess.interfaces.SubscriptionsRepository;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;

import javax.inject.Singleton;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class InMemorySubscriptionsRepository implements SubscriptionsRepository {
    private final ConcurrentHashMap<SubscriptionId, Subscription> subscriptions = new ConcurrentHashMap<>();

    @Override
    public ImmutableList<Subscription> getAllSubscriptions() {
        return ImmutableList.copyOf(subscriptions.values());
    }

    @Override
    public ImmutableList<Subscription> getTopicsSubscriptions(TopicId topicId) {
        return ImmutableList.copyOf(
                subscriptions.values()
                .stream()
                .filter(s -> s.getTopicId().equals(topicId))::iterator);
    }

    @Override
    public Subscription getSubscription(SubscriptionId subscriptionId) {
        return subscriptions.getOrDefault(subscriptionId, null);
    }

    @Override
    public void createSubscription(Subscription subscription) {
        subscriptions.put(subscription.getSubscriptionId(), subscription);
    }

    @Override
    public void deleteSubscription(SubscriptionId subscriptionId) {
        subscriptions.remove(subscriptionId);
    }
}
