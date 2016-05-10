package com.godaddy.pubsub.dataAccess.interfaces;

import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface MessageRepository {
    void createSubscription(Subscription sub);

    void createTopic(Topic topic);

    void deleteSubscription(Subscription sub);

    void deleteTopic(Topic topic);

    void setListener(Subscription sub, MessageDeliveryListener listener);

    void removeListener(Subscription sub);

    void publish(Topic topic, Message msg) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException;

    void init();

    void cleanup();
}
