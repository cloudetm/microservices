package com.godaddy.pubsub.resources.api.v1;

import com.godaddy.logging.Logger;
import com.godaddy.logging.LoggerFactory;
import com.godaddy.pubsub.dataAccess.interfaces.MessageMiddlewareDriver;
import com.godaddy.pubsub.dataAccess.interfaces.SubscriptionsRepository;
import com.godaddy.pubsub.dataAccess.interfaces.TopicsRepository;
import com.godaddy.pubsub.delivery.HttpDeliveryProtocol;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.errors.ErrorEntity;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.queues.interfaces.MessageDeliveryListener;
import com.godaddy.pubsub.queues.interfaces.MessageProcessorController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@AllArgsConstructor(onConstructor = @__(@Inject))
public class SubscriptionResource {
    public static final String ERROR_CODE_TOPIC_NOT_FOUND = "TOPIC_NOT_FOUND";
    public static final String ERROR_MESSAGE_TOPIC_NOT_FOUND = "Topic does not exist";

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionResource.class);

    private final MessageMiddlewareDriver messageMiddlewareDriver;
    private final MessageProcessorController messageProcessorController;
    private final SubscriptionsRepository subscriptionsRepository;
    private final TopicsRepository topicsRepository;

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
    public Response create(@Valid final Subscription subscription){
        final Topic topic = topicsRepository.getTopic(subscription.getTopicId());
        if(topic == null){
            final int unprocessableEntityStatusCode = 422;
            return Response.status(unprocessableEntityStatusCode)
                    .entity(
                            ErrorEntity.builder()
                                    .code(ERROR_CODE_TOPIC_NOT_FOUND)
                                    .message(ERROR_MESSAGE_TOPIC_NOT_FOUND)
                                    .stackTrace(Arrays.toString(Thread.currentThread().getStackTrace()))
                                    .build())
                    .build();
        }

        logger.info("Creating Subscription");

        Subscription subscriptionToCreate = subscription
                .toBuilder()
                .subscriptionId(SubscriptionId.random())
                .build();

        subscriptionsRepository.createSubscription(subscriptionToCreate);

        messageMiddlewareDriver.createSubscription(topic, subscriptionToCreate);

        messageProcessorController.addSubscription(subscriptionToCreate);

        messageMiddlewareDriver.setListener(subscriptionToCreate, messageListener);

        return Response.status(Response.Status.CREATED).entity(subscriptionToCreate).build();
    }
}
