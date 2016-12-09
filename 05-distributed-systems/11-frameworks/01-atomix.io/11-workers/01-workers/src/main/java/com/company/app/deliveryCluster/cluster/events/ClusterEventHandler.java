package com.company.app.deliveryCluster.cluster.events;

public class ClusterEventHandler {

    private final ClusterEventVisitor clusterEventVisitor;

    public ClusterEventHandler(final ClusterEventVisitor clusterEventVisitor) {
        this.clusterEventVisitor = clusterEventVisitor;
    }

    public void onClusterEvent(final ClusterEvent clusterEvent) {
        clusterEvent.acceptEventVisitor(clusterEventVisitor);
    }
}
