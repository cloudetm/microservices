package com.company.app.deliveryCluster.cluster.events;

import com.company.app.deliveryCluster.cluster.ClusterStateProvider;

public class ElectedLeaderClusterEvent extends ClusterEvent {
    public ElectedLeaderClusterEvent(final ClusterStateProvider clusterStateProvider) {
        super(clusterStateProvider);
    }

    @Override
    public void acceptEventVisitor(final ClusterEventVisitor visitor) {
        visitor.visit(this);
    }
}

