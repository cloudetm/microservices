package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.serialization.JacksonJsonMapper;
import com.godaddy.pubsub.serialization.JsonMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class JsonMapperModule extends AbstractModule {

    @Override protected void configure() {
    }

    @Provides
    @Singleton
    public JsonMapper getJsonMapper() {
        return new JacksonJsonMapper();
    }
}
