package com.godaddy.pubsub.queues;

import com.godaddy.logging.Logger;
import com.godaddy.logging.LoggerFactory;
import com.godaddy.pubsub.dataAccess.interfaces.SubscriptionsRepository;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.queues.interfaces.MessageProcessor;
import com.godaddy.pubsub.queues.interfaces.MessageProcessorController;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class DefaultMessageProcessorController implements MessageProcessorController {
    private static Logger logger = LoggerFactory.getLogger(DefaultMessageProcessorController.class);

    private final Map<SubscriptionId, MessageProcessor> messageProcessorMap = new ConcurrentHashMap<>();

    private final SubscriptionsRepository subscriptionsRepository;
    private final MessageProcessor.Factory messageProcessFactory;

    @Override
    public void start() {
        logger.info("Retrieving subscriptions");
        final ImmutableList<Subscription> subscriptions = subscriptionsRepository.getAllSubscriptions();
        subscriptions.stream().forEach(this::createAndStartMessageProcess);
    }

    @Override
    public void stop() throws Exception {
        messageProcessorMap
                .values()
                .stream()
                .forEach(messageProcessor -> {
                    try {
                        messageProcessor.stop();
                    } catch (Exception e) {
                        logger.error(e, "Error stopping message processor");
                    }
                });
    }

    @Override
    public void addSubscription(Subscription subscription) {
        createAndStartMessageProcess(subscription);
    }

    private void createAndStartMessageProcess(final Subscription subscription){
        final MessageProcessor messageProcessor = messageProcessFactory.create(subscription);
        messageProcessorMap.put(subscription.getSubscriptionId(), messageProcessor);

        logger.info("Starting message process");
        messageProcessor.start();
    }
}
