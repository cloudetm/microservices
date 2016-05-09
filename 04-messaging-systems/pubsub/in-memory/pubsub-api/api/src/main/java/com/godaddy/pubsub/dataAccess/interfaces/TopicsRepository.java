package com.godaddy.pubsub.dataAccess.interfaces;

import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;

public interface TopicsRepository {
    ImmutableList<Topic> getAllTopics();
    Topic getTopic(TopicId topicId);
    void createTopic(Topic topic);
    void deleteTopic(TopicId topicId);
}

