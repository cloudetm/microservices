package com.godaddy.pubsub.dataAccess;

import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.godaddy.pubsub.pub.model.topics.TopicNameDescriptor;
import com.google.common.base.Joiner;

public final class TopicNamingScheme {
    public TopicId buildTopicId(TopicNameDescriptor nameDescriptor) {

        final Joiner dotJoiner = Joiner.on('.').skipNulls();
        final String topicIdRaw = dotJoiner.join(nameDescriptor.getTeam(),
                                                 nameDescriptor.getProject(),
                                                 nameDescriptor.getEntity(),
                                                 nameDescriptor.getEventType(),
                                                 "v" + nameDescriptor.getMajorVersion());

        return TopicId.valueOf(topicIdRaw.toLowerCase());
    }
}
