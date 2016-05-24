package com.godaddy.pubsub.pub.model.messages;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class MessageTicket {

    @NotNull
    private final MessageId messageId;
}
