package com.godaddy.pubsub.dataAccess;


import com.godaddy.pubsub.dataAccess.interfaces.TopicsRepository;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class InMemoryTopicsRepository implements TopicsRepository {
    private final ConcurrentHashMap<TopicId, Topic> topics = new ConcurrentHashMap<>();

    @Inject
    public InMemoryTopicsRepository() {
    }

    @Override
    public ImmutableList<Topic> getAllTopics() {
        return ImmutableList.copyOf(topics.values());
    }

    @Override
    public Topic getTopic(final TopicId topicId) {
        return topics.getOrDefault(topicId, null);
    }

    @Override
    public void createTopic(final Topic topic) {
        topics.put(topic.getTopicId(), topic);
    }

    @Override
    public void deleteTopic(final TopicId topicId) {
        topics.remove(topicId);
    }
}
