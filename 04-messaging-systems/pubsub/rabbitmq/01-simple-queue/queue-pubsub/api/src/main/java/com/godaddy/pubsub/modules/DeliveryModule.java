package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.delivery.DefaultDeliveryAction;
import com.godaddy.pubsub.delivery.interfaces.DeliveryAction;
import com.google.inject.AbstractModule;

public class DeliveryModule extends AbstractModule {

    @Override protected void configure() {
        bind(DeliveryAction.class).to(DefaultDeliveryAction.class);
    }
}
