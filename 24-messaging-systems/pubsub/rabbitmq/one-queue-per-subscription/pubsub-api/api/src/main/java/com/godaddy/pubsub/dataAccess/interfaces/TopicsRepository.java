package com.godaddy.pubsub.dataAccess.interfaces;

import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;

// config store
public interface TopicsRepository {
    ImmutableList<Topic> getAllTopics();
    Topic getTopic(final TopicId topicId);
    void createTopic(final Topic topic);
    void deleteTopic(final TopicId topicId);
}
