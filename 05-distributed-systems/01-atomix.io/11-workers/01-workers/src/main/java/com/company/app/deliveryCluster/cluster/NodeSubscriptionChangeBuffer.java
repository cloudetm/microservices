package com.company.app.deliveryCluster.cluster;

import com.company.app.deliveryCluster.allocation.AllocatedSubscription;
import com.company.app.deliveryCluster.allocation.NodeSubscriptionChange;
import com.company.app.deliveryCluster.allocation.NodeSubscriptionChangeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NodeSubscriptionChangeBuffer {
    private HashMap<ClusterNodeReference, HashMap<AllocatedSubscription, NodeSubscriptionChangeType>> changeBuffer
            = new HashMap<>();

    public synchronized void accumelate(final Collection<NodeSubscriptionChange> changes) {

        for (NodeSubscriptionChange change : changes) {
            HashMap<AllocatedSubscription, NodeSubscriptionChangeType> subscriptionChanges
                    = changeBuffer.get(change.getNode());


            // Allocate subscription changes
            if (subscriptionChanges == null) {
                subscriptionChanges = new HashMap<>();
                this.changeBuffer.put(change.getNode(), subscriptionChanges);
            }

            if (subscriptionChanges.containsKey(change.getSubscription())) {

                NodeSubscriptionChangeType changeType = subscriptionChanges.get(change.getSubscription());

                // Remove any changes if we add then remove a subscription or vice versa
                if (changeType != change.getChangeType()) {
                    subscriptionChanges.remove(change.getSubscription());
                }
            }
            else {
                subscriptionChanges.put(change.getSubscription(), change.getChangeType());
            }
        }
    }

    public synchronized Collection<NodeSubscriptionChange> drain() {
        ArrayList<NodeSubscriptionChange> changes = new ArrayList<>();

        this.changeBuffer.forEach((node, subscriptions) -> {
            subscriptions.forEach((subscription, change) -> {
                changes.add(new NodeSubscriptionChange(change, node, subscription));
            });
        });

        this.changeBuffer = new HashMap<>();
        return changes;
    }
}
