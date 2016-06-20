package com.company.app;

import com.company.app.deliveryCluster.cluster.Cluster;
import com.company.app.deliveryCluster.cluster.ClusterNodeMetadata;
import com.company.app.deliveryCluster.cluster.ClusterSeedList;
import com.company.app.deliveryCluster.cluster.atomix.AtomixClusterNode;
import com.company.app.deliveryCluster.cluster.atomix.AtomixClusterReplicaBuilder;
import com.company.app.deliveryCluster.cluster.events.ClusterEventHandler;
import com.google.inject.Inject;

import java.util.concurrent.TimeoutException;

//TODO Add and Get Bootstrap list from Cassandra

public class LocalAtomixCluster implements Cluster {

    private final ClusterSeedList clusterSeedList;
    private final ClusterEventHandler clusterEventHandler;
    private final AtomixClusterReplicaBuilder replicaBuilder;

    @Inject
    public LocalAtomixCluster(
            final AtomixClusterReplicaBuilder replicaBuilder,
            final ClusterSeedList clusterSeedList,
            final ClusterEventHandler clusterEventHandler) {
        this.replicaBuilder = replicaBuilder;
        this.clusterSeedList = clusterSeedList;
        this.clusterEventHandler = clusterEventHandler;
    }

    public AtomixClusterNode startAndJoinCluster(ClusterNodeMetadata nodeMetadata) throws InterruptedException {

        AtomixClusterNode clusterNode = new AtomixClusterNode(nodeMetadata, this.replicaBuilder, this.clusterSeedList, clusterEventHandler);

        try {
            clusterNode.joinCluster();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return clusterNode;
    }
}
