package com.godaddy.pubsub.queues.rabbitmq;

import com.godaddy.pubsub.model.ShardName;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.queues.interfaces.ShardStrategy;

public class RabbitMqConfigShardStrategy implements ShardStrategy {
    @Override
    public ShardName shardForTopic(Topic topic) {
        return ShardName.valueOf(topic.getNameDescriptor().getTeam());
    }
}
