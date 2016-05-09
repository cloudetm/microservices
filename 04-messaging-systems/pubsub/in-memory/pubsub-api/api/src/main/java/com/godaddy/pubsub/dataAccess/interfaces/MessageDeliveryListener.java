package com.godaddy.pubsub.dataAccess.interfaces;

import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;

public interface MessageDeliveryListener {
    public boolean deliverMessage(SubscriptionId subId, Message msg);
}
