package com.godaddy.pubsub.delivery.interfaces;

import com.godaddy.pubsub.model.DeliveryEnvelope;

import javax.ws.rs.core.Response;

public interface DeliveryAction {
    Response onDelivery(DeliveryEnvelope envelope);
}
