package com.company.app.deliveryCluster.cluster.events;

public interface ClusterEventHandler {
    void onClusterEvent(final ClusterEvent clusterEvent);
}
