package com.godaddy.pubsub.resources.api.v1;

import com.godaddy.pubsub.delivery.interfaces.DeliveryAction;
import com.godaddy.pubsub.model.DeliveryEnvelope;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.messages.MessageId;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("/api/v1/delivery")
@Api(tags = "Delivery", description = "PubSub outbound API for delivering messages, as implemented by subscribers")
@Produces(MediaType.WILDCARD)
@AllArgsConstructor(onConstructor = @__(@Inject))
public class DeliveryResource {

    private final DeliveryAction deliveryAction;

    @POST
    @ApiOperation(value = "Accept a message as a subscriber",
            notes = "This endpoint defines the signature that a Subscription.messageDeliveryUri must implement. " +
                    "The path listed above is not significant; the subscriber-provided messageDeliveryUri is absolute. " +
                    "(e.g. http://my-product-team.int.godaddy.com/purchase-notification). The fields listed as required " +
                    "below are constraints only on the PubSub platform and may be ignored silently at the the subscriber's " +
                    "discretion." )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "PROCESSED (any 2xx), Any body will be ignored by the PubSub service"),
            @ApiResponse(code = 204, message = "PROCESSED, The expected typical response"),
            @ApiResponse(code = 429, message = "TOO MANY REQUESTS, Message should be redelivered after a backoff"),
            @ApiResponse(code = 500, message = "ERROR (any non-2xx), Message should be redelivered")
    })
    @Consumes({ MediaType.WILDCARD })
    public Response acceptMessage(
            @ApiParam(value = "Topic id this message was published to", example = "ecomm.fulfillment.receipt.created.v1", required = true)
            @HeaderParam("X-Pubsub-Topic-Id") final String topicId,
            @ApiParam(value = "Subscription id served by this delivery", required = true)
            @HeaderParam("X-Pubsub-Subscription-Id") final String subscriptionId,
            @ApiParam(value = "Time the message was published", required = true)
            @HeaderParam("X-Pubsub-Timestamp") final String timestamp,
            @ApiParam(value = "Unique message id issued by the pubsub system", required = true)
            @HeaderParam("X-Pubsub-Message-Id") final String messageId,
            @ApiParam(value = "Request id passed through from the publisher")
            @HeaderParam("X-Request-Id") final String requestId,
            final InputStream messageData) throws Exception {

        DeliveryEnvelope envelope = DeliveryEnvelope.builder()
                .topicId(TopicId.valueOf(topicId))
                .subscriptionId(SubscriptionId.valueOf(subscriptionId))
                .message(Message.builder()
                        .messageId(MessageId.valueOf(messageId))
                        .requestId(requestId)
                        .timestamp(DateTime.parse(timestamp))
                        .data(ByteStreams.toByteArray(messageData))
                        .build())
                .build();

        return deliveryAction.onDelivery(envelope);
    }
}
