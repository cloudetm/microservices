package com.godaddy.pubsub.dataAccess;

import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.godaddy.pubsub.pub.model.topics.TopicNameDescriptor;
import com.google.common.base.Joiner;
import lombok.NonNull;

public final class TopicNamingScheme {
    public TopicId buildTopicId(@NonNull final TopicNameDescriptor nameDescriptor){
        Joiner dotJoiner = Joiner.on('.').skipNulls();
        final String topicIdRaw = dotJoiner.join(
                nameDescriptor.getTeam(),
                nameDescriptor.getProject(),
                nameDescriptor.getEntity(),
                nameDescriptor.getEventType(),
                "v" + nameDescriptor.getMajorVersion());
        return TopicId.valueOf(topicIdRaw.toLowerCase());
    }
}
