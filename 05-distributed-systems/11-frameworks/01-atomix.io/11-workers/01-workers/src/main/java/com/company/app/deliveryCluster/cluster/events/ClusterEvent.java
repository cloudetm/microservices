package com.company.app.deliveryCluster.cluster.events;

import com.company.app.deliveryCluster.cluster.ClusterStateProvider;
import lombok.Getter;

public abstract class ClusterEvent {

    @Getter
    private final ClusterStateProvider clusterStateProvider;

    protected ClusterEvent(final ClusterStateProvider clusterStateProvider) {
        this.clusterStateProvider = clusterStateProvider;
    }

    public abstract void acceptEventVisitor(final ClusterEventVisitor visitor);
}

