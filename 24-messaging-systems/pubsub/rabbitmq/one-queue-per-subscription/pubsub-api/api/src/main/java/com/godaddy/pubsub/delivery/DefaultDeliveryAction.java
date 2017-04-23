package com.godaddy.pubsub.delivery;

import com.godaddy.logging.Logger;
import com.godaddy.logging.LoggerFactory;
import com.godaddy.pubsub.delivery.interfaces.DeliveryAction;
import com.godaddy.pubsub.model.DeliveryEnvelope;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DefaultDeliveryAction implements DeliveryAction {
    private static final Logger logger = LoggerFactory.getLogger(DefaultDeliveryAction.class);

    @Override
    public Response onDelivery(DeliveryEnvelope envelope) {
        logger.info("Accepting delivery of message '{}' for subscription '{}'",
                envelope.getMessage().getMessageId(),
                envelope.getSubscriptionId());

        return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(envelope)
                .build();
    }
}
