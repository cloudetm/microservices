package com.godaddy.pubsub.modules;

import com.godaddy.pubsub.pub.Mappers;
import com.google.inject.AbstractModule;
import io.paradoxical.rabbitmq.DefaultSerializer;
import io.paradoxical.rabbitmq.ListenerOptions;
import io.paradoxical.rabbitmq.QueueSerializer;

public class ListenerOptionsModule extends AbstractModule {
    private static class JsonQueueSerializer extends DefaultSerializer{
        JsonQueueSerializer(){
            super(Mappers.json());
        }
    }
    @Override
    protected void configure() {
        bind(QueueSerializer.class).toInstance(new JsonQueueSerializer());
    }

    public ListenerOptions defaultListenerOptions(final QueueSerializer serializer){
        return ListenerOptions.builder()
                .serializer(serializer)
                .build();
    }
}
