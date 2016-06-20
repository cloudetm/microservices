package com.company.app.deliveryCluster.cluster;

import java.net.InetSocketAddress;
import java.util.Collection;

public interface ClusterSeedList {
    void addSeedClusterNodeMetadata(final ClusterNodeMetadata clusterNodeMetadata);

    Collection<InetSocketAddress> getBootstrapAddresses();
}
