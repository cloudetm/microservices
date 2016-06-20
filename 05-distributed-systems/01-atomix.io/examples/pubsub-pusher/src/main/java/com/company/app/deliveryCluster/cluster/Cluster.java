package com.company.app.deliveryCluster.cluster;

import com.company.app.deliveryCluster.cluster.atomix.AtomixClusterNode;

public interface Cluster {
    AtomixClusterNode startAndJoinCluster(final ClusterNodeMetadata nodeMetadata) throws Exception;
}
