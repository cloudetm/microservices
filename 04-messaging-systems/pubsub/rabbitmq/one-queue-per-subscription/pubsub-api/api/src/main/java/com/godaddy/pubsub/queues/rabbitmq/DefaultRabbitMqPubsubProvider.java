package com.godaddy.pubsub.queues.rabbitmq;

import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.godaddy.pubsub.queues.interfaces.ShardStrategy;
import com.godaddy.pubsub.queues.rabbitmq.interfaces.RabbitMqPubsubProvider;
import io.paradoxical.rabbitmq.Exchange;
import io.paradoxical.rabbitmq.Queue;

import javax.inject.Inject;

public class DefaultRabbitMqPubsubProvider implements RabbitMqPubsubProvider {

    private ShardStrategy shardStrategy;

    @Inject
    public DefaultRabbitMqPubsubProvider(final ShardStrategy shardStrategy){
        this.shardStrategy = shardStrategy;
    }

    @Override
    public Exchange getPublishExchange(Topic topic) {
        return new Exchange(Exchange.Type.Topic, shardStrategy.shardForTopic(topic).get());
    }

    @Override
    public Queue getQueue(SubscriptionId subscriptionId, TopicId topicId) {
        return Queue.valueOf("subscriptionId." + subscriptionId.get()).withRoute(topicId.get());
    }
}
