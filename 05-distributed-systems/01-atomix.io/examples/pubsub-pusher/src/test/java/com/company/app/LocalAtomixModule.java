package com.company.app;

import com.company.app.deliveryCluster.allocation.SimpleSubscriptionAllocationStrategy;
import com.company.app.deliveryCluster.allocation.SubscriptionAllocationStrategy;
import com.company.app.deliveryCluster.cluster.Cluster;
import com.company.app.deliveryCluster.cluster.ClusterSeedList;
import com.company.app.deliveryCluster.cluster.atomix.AtomixClusterReplicaBuilder;
import com.company.app.deliveryCluster.cluster.events.ClusterEventHandler;
import com.company.app.deliveryCluster.cluster.events.ClusterEventVisitor;
import com.company.app.deliveryCluster.deliveryNode.DefaultClusterEventHandler;
import com.company.app.deliveryCluster.deliveryNode.DeliveryNodeClusterEventVistor;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import static jdk.nashorn.internal.objects.NativeFunction.bind;

public class LocalAtomixModule extends AbstractModule {
    protected void configure() {

        // Replace with NettyTransport Atomix cluster
        bind(AtomixClusterReplicaBuilder.class).to(LocalAtomixClusterReplicaBuilder.class);
        bind(Cluster.class).to(LocalAtomixCluster.class);
        bind(ClusterEventVisitor.class).to(DeliveryNodeClusterEventVistor.class);

//        bind(MessageProcessorController.class).to(MockMessageProcessorController.class);

        // Replace with Cassandra based bootstrap list
        bind(ClusterSeedList.class).to(LocalClusterSeedList.class);

        bind(SubscriptionAllocationStrategy.class).to(SimpleSubscriptionAllocationStrategy.class);
    }

    @Provides
    public ClusterEventHandler clusterEventHandler(final ClusterEventVisitor clusterEventVisitor) {
        return new DefaultClusterEventHandler(clusterEventVisitor);
    }
}
