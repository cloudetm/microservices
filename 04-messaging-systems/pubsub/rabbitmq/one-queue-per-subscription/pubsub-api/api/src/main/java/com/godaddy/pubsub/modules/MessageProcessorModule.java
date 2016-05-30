package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.queues.DefaultMessageProcessorController;
import com.godaddy.pubsub.queues.interfaces.MessageProcessor;
import com.godaddy.pubsub.queues.rabbitmq.DefaultMessageProcessor;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class MessageProcessorModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
        .implement(MessageProcessor.class, DefaultMessageProcessor.class)
        .build(MessageProcessor.Factory.class));
    }
}
