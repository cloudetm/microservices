package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.delivery.DefaultDeliveryAction;
import com.godaddy.pubsub.delivery.HttpDeliveryProtocol;
import com.godaddy.pubsub.delivery.interfaces.DeliveryAction;
import com.godaddy.pubsub.delivery.interfaces.DeliveryProtocol;
import com.google.inject.AbstractModule;

public class DeliveryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DeliveryAction.class).to(DefaultDeliveryAction.class);
        bind(DeliveryProtocol.class).to(HttpDeliveryProtocol.class);
    }
}
