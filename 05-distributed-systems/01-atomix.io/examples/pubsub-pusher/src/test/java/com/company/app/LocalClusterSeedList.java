package com.company.app;

import com.company.app.deliveryCluster.cluster.ClusterNodeMetadata;
import com.company.app.deliveryCluster.cluster.ClusterSeedList;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;

public class LocalClusterSeedList implements ClusterSeedList {
    private ArrayList<InetSocketAddress> addresses = new ArrayList<>();

    @Override
    public synchronized void addSeedClusterNodeMetadata(final ClusterNodeMetadata clusterNodeMetadata) {
        this.addresses.add(clusterNodeMetadata.address()) ;
    }

    @Override
    public synchronized Collection<InetSocketAddress> getBootstrapAddresses() {
        return this.addresses;
    }
}
