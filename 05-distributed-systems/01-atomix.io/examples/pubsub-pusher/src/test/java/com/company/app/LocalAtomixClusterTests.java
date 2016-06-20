package com.company.app;

import com.company.app.deliveryCluster.cluster.Cluster;
import com.company.app.deliveryCluster.cluster.atomix.AtomixClusterNode;
import com.company.app.deliveryCluster.cluster.atomix.AtomixClusterNodeMetadata;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.netflix.governator.InjectorBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LocalAtomixClusterTests {

    @Test
    public void simple_local_atomix_test() throws Exception {

        List<Module> moduels = new ArrayList<>();
        moduels.add(new LocalAtomixModule());

        Injector injector = InjectorBuilder.fromModules(moduels).createInjector();
        Cluster cluster = injector.getInstance(Cluster.class);

        List<AtomixClusterNode> clusterNodes = new ArrayList<>();

        int port = 5000;
        for (int zone = 0; zone < 2; ++zone) {
            for (int nodeCount = 0; nodeCount < 2; ++nodeCount) {
                AtomixClusterNode clusterNode = cluster.startAndJoinCluster(new AtomixClusterNodeMetadata("localhost", port, zone));
                clusterNode.broadcastChanges();
                clusterNodes.add(clusterNode);
                clusterNodes.get(0).broadcastChanges();
                ++port;
                Thread.sleep(1000);
            }
        }

//        AtomixClusterNode atomixClusterNode = clusterNodes.get(0);
//        atomixClusterNode.broadcastChanges();
    }
}
