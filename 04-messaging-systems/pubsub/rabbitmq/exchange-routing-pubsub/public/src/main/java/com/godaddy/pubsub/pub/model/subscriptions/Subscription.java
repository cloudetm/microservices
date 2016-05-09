package com.godaddy.pubsub.pub.model.subscriptions;

import com.godaddy.pubsub.pub.model.topics.TopicId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class Subscription {

    @ApiModelProperty(value = "the identifier for the subscription")
    private final SubscriptionId subscriptionId;

    @ApiModelProperty(value = "the name of the topic to subscribe to", example = "ecomm.fulfillment.receipt.created.v1", required = true)
    @NotNull
    private final TopicId topicId;

    @ApiModelProperty(example = "listens for created receipts in order to send a confirmation email")
    private final String description;

    @ApiModelProperty(value = "the uri that topic messages will be delivered to", example = "http://localhost:8080/api/v1/delivery", required = true)
    @NotNull
    private final SubscriberMessageDeliveryUri messageDeliveryUri;
}
