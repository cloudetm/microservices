package com.godaddy.pubsub.queues.rabbitmq;

import com.godaddy.pubsub.model.Message;
import io.paradoxical.rabbitmq.queues.EventBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RabbitMqMessage extends EventBase {
    private final Message message;
}
