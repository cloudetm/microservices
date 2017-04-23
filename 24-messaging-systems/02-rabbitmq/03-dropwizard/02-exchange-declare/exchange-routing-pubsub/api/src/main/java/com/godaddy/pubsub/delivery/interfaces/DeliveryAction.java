package com.godaddy.pubsub.delivery.interfaces;

import com.godaddy.pubsub.model.DeliveryEnvelope;

import javax.ws.rs.core.Response;

public interface DeliveryAction {

    // null return indicates that a default response should be used
    Response onDelivery(DeliveryEnvelope envelope);
}
