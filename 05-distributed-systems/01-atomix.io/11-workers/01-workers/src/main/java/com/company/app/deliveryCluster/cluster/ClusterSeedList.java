package com.company.app.deliveryCluster.cluster;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;

public class ClusterSeedList {
    private ArrayList<InetSocketAddress> addresses = new ArrayList<>();

    public synchronized void addSeedClusterNodeMetadata(final ClusterNodeMetadata clusterNodeMetadata) {
        this.addresses.add(clusterNodeMetadata.address()) ;
    }

    public synchronized Collection<InetSocketAddress> getBootstrapAddresses() {
        return this.addresses;
    }
}
