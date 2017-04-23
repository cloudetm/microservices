package com.godaddy.pubsub.delivery.interfaces;

import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;

public interface DeliveryProtocol {
    boolean deliver(final Subscription sub, final Message msg);
}
