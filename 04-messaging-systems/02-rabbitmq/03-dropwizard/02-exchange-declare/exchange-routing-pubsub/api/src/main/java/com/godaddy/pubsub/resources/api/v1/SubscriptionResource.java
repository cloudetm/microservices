package com.godaddy.pubsub.resources.api.v1;

import com.godaddy.logging.Logger;
import com.godaddy.logging.LoggerFactory;
import com.godaddy.pubsub.dataAccess.interfaces.MessageDeliveryListener;
import com.godaddy.pubsub.dataAccess.interfaces.MessageRepository;
import com.godaddy.pubsub.dataAccess.interfaces.SubscriptionsRepository;
import com.godaddy.pubsub.dataAccess.interfaces.TopicsRepository;
import com.godaddy.pubsub.delivery.HttpDeliveryProtocol;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.errors.ErrorEntity;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Path("/api/v1/subscriptions")
@Api(tags = "Subscriptions", description = "PubSub subscriber api for managing subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor(onConstructor = @__(@Inject))
public class SubscriptionResource {
    public static final String ERROR_CODE_TOPIC_NOT_FOUND = "TOPIC_NOT_FOUND";
    public static final String ERROR_MESSAGE_TOPIC_NOT_FOUND = "Topic does not exist";

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionResource.class);

    private final MessageRepository messageRepository;
    private final SubscriptionsRepository subscriptionsRepository;
    private final TopicsRepository topicsRepository;

    // TODO: rework this into a pusher process
    private final HttpDeliveryProtocol deliveryProtocol = new HttpDeliveryProtocol();
    private final MessageDeliveryListener messageListener = new MessageDeliveryListener() {
        @Override
        public boolean deliverMessage(SubscriptionId subId, Message msg) {
            return deliveryProtocol.deliver(subscriptionsRepository.getSubscription(subId), msg);
        }
    };

    @POST
    @ApiOperation(value = "Subscribe to topic")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 422, message = "Invalid Subscription Creation Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Response create(@Valid Subscription subscription) {
        if (topicsRepository.getTopic(subscription.getTopicId()) == null) {
            return Response.status(422).entity(
                    ErrorEntity.builder()
                    .code(ERROR_CODE_TOPIC_NOT_FOUND)
                    .message(ERROR_MESSAGE_TOPIC_NOT_FOUND)
                    .stackTrace(Arrays.toString(Thread.currentThread().getStackTrace()))
                    .build()
            ).build();
        }

        logger.info("Creating Subscription");

        Subscription subscriptionToCreate = subscription
                .toBuilder()
                .subscriptionId(SubscriptionId.random())
                .build();

        subscriptionsRepository.createSubscription(subscriptionToCreate);
        messageRepository.createSubscription(subscriptionToCreate);

        // TODO: rework this into a pusher process
        messageRepository.setListener(subscriptionToCreate, messageListener);

        return Response.status(Response.Status.CREATED).entity(subscriptionToCreate).build();
    }
}
