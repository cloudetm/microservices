package com.godaddy.pubsub.model;

import com.godaddy.pubsub.pub.model.messages.MessageId;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.joda.time.DateTime;

@Value
@Builder
@AllArgsConstructor
public class Message {
    @NonNull
    private final TopicId topicId;

    @NonNull
    private final MessageId messageId;

    @NonNull
    private final String requestId;

    @NonNull
    private final DateTime timestamp;

    @NonNull
    private final byte[] data;
}
