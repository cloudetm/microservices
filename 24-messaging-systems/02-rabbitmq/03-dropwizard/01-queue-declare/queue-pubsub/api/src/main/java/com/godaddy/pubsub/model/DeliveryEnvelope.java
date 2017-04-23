package com.godaddy.pubsub.model;

import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class DeliveryEnvelope {

    @NonNull
    private final TopicId topicId;

    @NonNull
    private final SubscriptionId subscriptionId;

    @NonNull
    private final Message message;
}
