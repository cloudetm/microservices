package com.company.app.deliveryCluster.deliveryNode;

import com.company.app.deliveryCluster.cluster.events.ClusterEvent;
import com.company.app.deliveryCluster.cluster.events.ClusterEventHandler;
import com.company.app.deliveryCluster.cluster.events.ClusterEventVisitor;

public class DefaultClusterEventHandler implements ClusterEventHandler {

    private final ClusterEventVisitor clusterEventVisitor;

    public DefaultClusterEventHandler(final ClusterEventVisitor clusterEventVisitor) {
        this.clusterEventVisitor = clusterEventVisitor;
    }

    @Override
    public void onClusterEvent(final ClusterEvent clusterEvent) {
        clusterEvent.acceptEventVisitor(clusterEventVisitor);
    }
}
