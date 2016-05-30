package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.queues.DefaultMessageProcessorController;
import com.godaddy.pubsub.queues.interfaces.MessageProcessorController;
import com.google.inject.AbstractModule;

public class MessageProcessorControllerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MessageProcessorController.class).to(DefaultMessageProcessorController.class);
    }
}
