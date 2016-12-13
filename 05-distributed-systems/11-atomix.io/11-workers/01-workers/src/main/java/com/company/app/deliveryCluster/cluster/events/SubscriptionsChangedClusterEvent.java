package com.company.app.deliveryCluster.cluster.events;

import com.company.app.deliveryCluster.allocation.AllocatedSubscription;
import com.company.app.deliveryCluster.cluster.ClusterStateProvider;
import com.google.common.collect.ImmutableList;
import lombok.Getter;

public class SubscriptionsChangedClusterEvent extends ClusterEvent {
    @Getter
    private final ImmutableList<AllocatedSubscription> allocatedSubscriptions;

    public SubscriptionsChangedClusterEvent(
            final ClusterStateProvider clusterStateProvider,
            final ImmutableList<AllocatedSubscription> allocatedSubscriptions) {
        super(clusterStateProvider);
        this.allocatedSubscriptions = allocatedSubscriptions;
    }

    @Override
    public void acceptEventVisitor(final ClusterEventVisitor visitor) {
        visitor.visit(this);
    }
}
