package com.company.app.deliveryCluster.cluster.events;

import java.util.concurrent.CompletableFuture;

public interface ClusterEventVisitor {
    CompletableFuture<Void> visit(AllocationRequestedClusterEvent allocationRequestedClusterEvent);

    CompletableFuture<Void> visit(DesignatedFollowerClusterEvent designatedFollowerClusterEvent);

    CompletableFuture<Void> visit(ElectedLeaderClusterEvent electedLeaderClusterEvent);

    CompletableFuture<Void> visit(JoinedGroupClusterEvent joinedGroupClusterEvent);

    CompletableFuture<Void> visit(SubscriptionsChangedClusterEvent subscriptionsChangedClusterEvent);
}
