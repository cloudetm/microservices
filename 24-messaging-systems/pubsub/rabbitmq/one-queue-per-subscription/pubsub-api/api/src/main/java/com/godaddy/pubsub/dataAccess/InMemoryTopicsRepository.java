package com.godaddy.pubsub.dataAccess;

import com.godaddy.pubsub.dataAccess.interfaces.TopicsRepository;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;

import javax.inject.Singleton;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class InMemoryTopicsRepository implements TopicsRepository {
    private final ConcurrentHashMap<TopicId, Topic> topics = new ConcurrentHashMap<>();

    @Override
    public ImmutableList<Topic> getAllTopics() {
        return ImmutableList.copyOf(topics.values());
    }

    @Override
    public Topic getTopic(TopicId topicId) {
        return topics.getOrDefault(topicId, null);
    }

    @Override
    public void createTopic(Topic topic) {
        topics.put(topic.getTopicId(), topic);
    }

    @Override
    public void deleteTopic(TopicId topicId) {
        topics.remove(topicId);
    }
}
