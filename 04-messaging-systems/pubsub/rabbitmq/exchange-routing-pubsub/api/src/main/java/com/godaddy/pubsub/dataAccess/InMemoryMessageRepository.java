package com.godaddy.pubsub.dataAccess;

import com.godaddy.logging.Logger;
import com.godaddy.logging.LoggerFactory;
import com.godaddy.pubsub.dataAccess.interfaces.MessageDeliveryListener;
import com.godaddy.pubsub.dataAccess.interfaces.MessageRepository;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Singleton
public class InMemoryMessageRepository implements MessageRepository {
    private final Logger logger = LoggerFactory.getLogger(InMemoryMessageRepository.class);

    private final Map<TopicId, Set<SubscriptionId>> topicSubs = new ConcurrentHashMap<>();

    private final Map<SubscriptionId, Queue<Message>> subQueues = new ConcurrentHashMap<>();

    private final Map<SubscriptionId, MessageDeliveryListener> subListeners = new ConcurrentHashMap<>();

    private Thread deliveryThread = new Thread(() -> { deliverMessages(true); });

    private volatile boolean deliveryThreadActive = false;

    @Override
    public synchronized void createSubscription(final Subscription sub) {
        Set<SubscriptionId> subSet = topicSubs.get(sub.getTopicId());
        if ( subSet == null ) {
            throw new RuntimeException("Topic doesn't exist: " + sub.getTopicId());
        }

        if ( !subSet.contains(sub.getSubscriptionId()) ) {
            subSet.add(sub.getSubscriptionId());
            subQueues.put(sub.getSubscriptionId(), new ConcurrentLinkedQueue<>());
        }
    }

    @Override
    public synchronized void createTopic(final Topic topic) {
        if ( !topicSubs.containsKey(topic.getTopicId()) ) {
            topicSubs.put(topic.getTopicId(), Collections.newSetFromMap(new ConcurrentHashMap<>()));
        }
    }

    @Override
    public synchronized void deleteSubscription(final Subscription sub) {
        Set<SubscriptionId> subSet = topicSubs.get(sub.getTopicId());
        if ( subSet != null ) {
            subSet.remove(sub.getSubscriptionId());
        }
        subQueues.remove(sub.getSubscriptionId());
        subListeners.remove(sub.getSubscriptionId());
    }

    @Override
    public synchronized void deleteTopic(final Topic topic) {
        Set<SubscriptionId> subSet = topicSubs.get(topic.getTopicId());
        subSet.forEach(subId -> {
                    subQueues.remove(subId);
                    subListeners.remove(subId);
        });
        topicSubs.remove(topic.getTopicId());
    }

    @Override
    public synchronized void setListener(final Subscription sub, final MessageDeliveryListener listener) {
        Queue<Message> queue = subQueues.get(sub.getSubscriptionId());
        if ( queue == null ) {
            throw new RuntimeException("Subscription doesn't exist: " + sub.getTopicId());
        }

        subListeners.put(sub.getSubscriptionId(), listener);
    }

    @Override
    public void removeListener(final Subscription sub) {
        subListeners.remove(sub.getSubscriptionId());
    }

    @Override
    public void publish(final Topic topic, final Message msg) {
        Set<SubscriptionId> subSet = topicSubs.get(topic.getTopicId());
        if ( subSet == null ) {
            throw new RuntimeException("Topic doesn't exist: " + topic.getTopicId());
        }

        subSet.forEach(subId -> {
            Queue<Message> queue = subQueues.get(subId);
            if (queue != null) {
                queue.add(msg);
            }
        });

        synchronized (this) {
            notify();
        }
    }

    public boolean isDeliveryThreadAlive() {
        return deliveryThread.isAlive();
    }

    public void deliverMessages(boolean threaded) {
        logger.info("message delivery thread started");

        boolean foundMessage = false;

        do {
            foundMessage = false;

            for ( Map.Entry<SubscriptionId, MessageDeliveryListener> ent : subListeners.entrySet() ) {
                if (!deliveryThreadActive && threaded) {
                    break;
                }

                SubscriptionId subId = ent.getKey();
                MessageDeliveryListener listener = ent.getValue();

                Queue<Message> queue = subQueues.get(subId);
                if (queue != null && !queue.isEmpty()) {
                    foundMessage = true;
                    try {
                        if (listener.deliverMessage(subId, queue.peek())) {
                            queue.remove();
                        }
                    } catch (Exception ex) {
                        logger.info("delivery failure for subscription '{}': {}", subId, ex.getMessage());
                    }
                }
            }

            if ( deliveryThreadActive && !foundMessage && threaded ) {
                try {
                    synchronized(this) {
                        wait();
                    }
                } catch (InterruptedException ex) {
                    ; // loop break condition should be set
                }
            }
        } while ( deliveryThreadActive || (!threaded && foundMessage) );

        logger.info("message delivery thread exiting");
    }

    @Override
    public synchronized void init() {
        if ( !deliveryThread.isAlive() ) {
            logger.info("starting message delivery thread...");
            deliveryThreadActive = true;
            deliveryThread.start();
        }
    }

    @Override
    public void cleanup() {
        final int MAX_DELIVERY_HALT_TIME_MS = 3000;

        logger.info("stopping message delivery thread...");
        deliveryThreadActive = false;
        synchronized (this) {
            notify();
        }

        try {
            deliveryThread.join(MAX_DELIVERY_HALT_TIME_MS);
        } catch (Exception ex) {
            logger.error(ex, "exception waiting for delivery thread to halt");
        }
    }
}
