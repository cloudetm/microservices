package com.godaddy.pubsub.queues.interfaces;

import com.godaddy.pubsub.model.ShardName;
import com.godaddy.pubsub.pub.model.topics.Topic;

public interface ShardStrategy {
    ShardName shardForTopic(Topic topic);
}
