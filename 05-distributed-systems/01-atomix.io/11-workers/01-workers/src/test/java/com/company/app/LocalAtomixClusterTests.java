package com.company.app;

import com.company.app.deliveryCluster.allocation.SubscriptionAllocationStrategy;
import com.company.app.deliveryCluster.cluster.ClusterNodeMetadata;
import com.company.app.deliveryCluster.cluster.ClusterSeedList;
import com.company.app.deliveryCluster.cluster.atomix.AtomixClusterNode;
import com.company.app.deliveryCluster.cluster.events.ClusterEventHandler;
import com.company.app.deliveryCluster.deliveryNode.DeliveryNodeClusterEventVistor;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.netflix.governator.InjectorBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class LocalAtomixClusterTests {

    @Test
    public void simple_local_atomix_test() throws Exception {

//        NodeBuilder nodeBuilder = new NodeBuilder();
        ClusterSeedList clusterSeedList = new ClusterSeedList();
        DeliveryNodeClusterEventVistor clusterEventVisitor = new DeliveryNodeClusterEventVistor(new SubscriptionAllocationStrategy());
        ClusterEventHandler clusterEventHandler = new ClusterEventHandler(clusterEventVisitor);

        List<AtomixClusterNode> clusterNodes = new ArrayList<>();
        int port = 5000;

        ClusterNodeMetadata nodeMetadata = new ClusterNodeMetadata("localhost", port, 0);

        for (int nodeCount = 0; nodeCount < 2; ++nodeCount) {
            AtomixClusterNode clusterNode = new AtomixClusterNode(nodeMetadata, clusterSeedList, clusterEventHandler);

            try {
                clusterNode.joinCluster();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

            clusterNode.broadcastChanges();
            clusterNodes.add(clusterNode);
            clusterNodes.get(0).broadcastChanges();
            ++port;
            Thread.sleep(1000);
        }
    }
}
