package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.queues.RabbitMqMessageListener;
import com.godaddy.pubsub.queues.interfaces.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ListenerModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
        .implement(Listener.class, RabbitMqMessageListener.class)
        .build(Listener.Factory.class));
    }
}
