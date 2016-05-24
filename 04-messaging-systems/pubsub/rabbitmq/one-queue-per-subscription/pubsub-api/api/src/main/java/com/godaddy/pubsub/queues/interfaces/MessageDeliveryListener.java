package com.godaddy.pubsub.queues.interfaces;

import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;

public interface MessageDeliveryListener {
    boolean deliverMessage(SubscriptionId subId, Message msg);
}
