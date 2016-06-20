package com.company.app.deliveryCluster.cluster;

import com.company.app.deliveryCluster.allocation.AllocatedSubscription;
import com.company.app.deliveryCluster.allocation.NodeSubscriptionChange;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ClusterStateProvider {
    CompletableFuture<Map<ClusterNodeReference, Collection<AllocatedSubscription>>> getAllocatedSubscriptions();

    Collection<ClusterNodeReference> getAvailableNodes();

    void applySubscriptionChanges(Collection<NodeSubscriptionChange> changes);

    boolean isLeader();
}
