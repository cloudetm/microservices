package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.dataAccess.InMemorySubscriptionsRepository;
import com.godaddy.pubsub.dataAccess.InMemoryTopicsRepository;
import com.godaddy.pubsub.dataAccess.interfaces.SubscriptionsRepository;
import com.godaddy.pubsub.dataAccess.interfaces.TopicsRepository;
import com.google.inject.AbstractModule;

public class DataAccessModule extends AbstractModule {

    @Override protected void configure() {
        bind(TopicsRepository.class).to(InMemoryTopicsRepository.class);
        bind(SubscriptionsRepository.class).to(InMemorySubscriptionsRepository.class);
    }
}
