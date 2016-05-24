package com.godaddy.pubsub.queues.rabbitmq.interfaces;

import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import io.paradoxical.rabbitmq.Exchange;
import io.paradoxical.rabbitmq.Queue;

public interface RabbitMqPubsubProvider {
    Exchange getPublishExchange(final Topic topic);
    Queue getQueue(final SubscriptionId subscriptionId, final TopicId topicId);
}
