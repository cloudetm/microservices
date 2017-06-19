package com.godaddy.pubsub.unittests.subscriptions;

import com.godaddy.pubsub.dataAccess.InMemorySubscriptionsRepository;
import com.godaddy.pubsub.dataAccess.interfaces.SubscriptionsRepository;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionsRespositoryTests {
    private final SubscriptionsRepository repository = new InMemorySubscriptionsRepository();

    private final PodamFactory fixture = new PodamFactoryImpl();

    @Test
    public void afterAddingASubscriptionItShouldBeGettable() {

        Subscription subscription = createSubscription();

        repository.createSubscription(subscription);

        final Subscription repositorySubscription =
            repository.getSubscription(subscription.getSubscriptionId());

        assertThat(repositorySubscription)
            .withFailMessage("Subscription not found for added subscription id: " + subscription.getSubscriptionId())
            .isNotNull();
    }

    @Test
    public void afterAddingManySubscriptionsTheyShouldAllBeReturnedFromGetAll() {

        final ImmutableList<Subscription> subscriptions = ImmutableList.of(
            createSubscription(),
            createSubscription(),
            createSubscription(),
            createSubscription());


        subscriptions.forEach(repository::createSubscription);

        final ImmutableList<Subscription> allSubscriptions = repository.getAllSubscriptions();

        assertThat(allSubscriptions)
            .containsAll(subscriptions)
            .hasSize(subscriptions.size());
    }

    @Test
    public void afterDeletingASubscriptionItShouldNotBeReturned() {
        Subscription subscription = createSubscription();

        repository.createSubscription(subscription);

        {
            final Subscription repositorySubscription =
                repository.getSubscription(subscription.getSubscriptionId());

            assertThat(repositorySubscription)
                .withFailMessage("Subscription not found for added subscription id: " + subscription.getTopicId())
                .isNotNull();
        }

        repository.deleteSubscription(subscription.getSubscriptionId());

        {
            final Subscription repositorySubscription =
                repository.getSubscription(subscription.getSubscriptionId());

            assertThat(repositorySubscription).isNull();
        }

    }

    @Test
    public void gettingSubscriptionsByTopicIdShouldFilter() {

        final int expectedNumberOfTopics = 1;
        final TopicId topicId = TopicId.valueOf("test");
        final Subscription topicSubscription =
            createSubscription().toBuilder()
                                .topicId(topicId)
                                .build();

        final ImmutableList<Subscription> subscriptions = ImmutableList.of(
            topicSubscription,
            createSubscription(),
            createSubscription(),
            createSubscription());


        subscriptions.forEach(repository::createSubscription);

        final ImmutableList<Subscription> allSubscriptions = repository.getTopicSubscriptions(topicId);

        assertThat(allSubscriptions)
            .contains(topicSubscription)
            .hasSize(expectedNumberOfTopics);
    }

    private Subscription createSubscription() {

        return fixture.manufacturePojo(Subscription.class)
                      .toBuilder()
                      .subscriptionId(SubscriptionId.random())
                      .build();

    }
}