package com.company.app.deliveryCluster.cluster.atomix;

import com.company.app.deliveryCluster.allocation.AllocatedSubscription;
import com.company.app.deliveryCluster.cluster.ClusterNodeMetadata;
import com.company.app.deliveryCluster.cluster.ClusterSeedList;
import com.company.app.deliveryCluster.cluster.ClusterStateProvider;
import com.company.app.deliveryCluster.cluster.NodeSubscriptionChangeBuffer;
import com.company.app.deliveryCluster.cluster.events.*;
import com.google.common.collect.ImmutableList;
import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.local.LocalServerRegistry;
import io.atomix.catalyst.transport.local.LocalTransport;
import io.atomix.collections.DistributedMultiMap;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.group.DistributedGroup;
import io.atomix.group.LocalMember;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class AtomixClusterNode {
    private final ClusterNodeMetadata nodeMetadata;
    private final ClusterEventHandler clusterEventHandler;
    private final ClusterSeedList clusterSeedList;
    private DistributedGroup group;
    private DistributedMultiMap<String, AllocatedSubscription> nodeToSubscriptionMap;
    private LocalMember localMember;
    private NodeSubscriptionChangeBuffer changeBuffer = new NodeSubscriptionChangeBuffer();

    private final LocalServerRegistry registry = new LocalServerRegistry();

    public AtomixClusterNode(
            final ClusterNodeMetadata nodeMetadata,
            final ClusterSeedList clusterSeedList,
            final ClusterEventHandler clusterEventHandler) {

        this.nodeMetadata = nodeMetadata;
        this.clusterEventHandler = clusterEventHandler;
//        this.replicaBuilder = replicaBuilder;
        this.clusterSeedList = clusterSeedList;
    }

    private ClusterStateProvider getStateProvider() {
        return new ClusterStateProvider(getIsLeader(), group.members(), nodeToSubscriptionMap, changeBuffer);
    }

    public void joinCluster() throws InterruptedException, TimeoutException {

        try {

            final Address localNodeAddress = new Address(nodeMetadata.address());

            AtomixReplica replica = AtomixReplica.builder(localNodeAddress)
                    .withTransport(new LocalTransport(this.registry))
                    .withStorage(Storage.builder()
                            .withDirectory("./target/atomix-replicas/" + UUID.randomUUID().toString())
                            .build())
                    .build();

//            AtomixReplica replica = replicaBuilder.buildReplica(localNodeAddress);

            clusterSeedList.addSeedClusterNodeMetadata(nodeMetadata);

            final List<Address> addressList =
                    clusterSeedList.getBootstrapAddresses().stream()
                            .map(Address::new)
                            .collect(Collectors.toList());

            replica.bootstrap(addressList).join();

            this.group = replica.getGroup(AtomixConstants.GroupName).get();

            this.localMember = group.join(nodeMetadata.id(), nodeMetadata).get();

            this.nodeToSubscriptionMap =
                    replica.<String, AllocatedSubscription>getMultiMap(AtomixConstants.SubscriptionMapName).join();

            this.group.onJoin(member -> {

                clusterEventHandler.onClusterEvent(new JoinedGroupClusterEvent(getStateProvider(), member.id()));
            });

            this.group.election().onElection(term -> {

                if (term.leader().equals(this.localMember)) {
                    clusterEventHandler.onClusterEvent(new ElectedLeaderClusterEvent(getStateProvider()));
                } else {
                    clusterEventHandler.onClusterEvent(new DesignatedFollowerClusterEvent(getStateProvider()));
                }
            });

            this.localMember.messaging().consumer(AtomixConstants.ResourceAllocationMessageBusName).onMessage(message -> {

                System.out.println("# node localMember consumer receive message");
                message.ack();

                String memberId = this.localMember.id();

                this.nodeToSubscriptionMap.get(memberId).thenAccept(allocatedSubscriptions -> {

                    final ImmutableList<AllocatedSubscription> subscriptions = ImmutableList.copyOf(allocatedSubscriptions);

                    clusterEventHandler.onClusterEvent(new SubscriptionsChangedClusterEvent(getStateProvider(), subscriptions));
                });
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void broadcastChanges() {

        System.out.println("# group producer send message");
        this.group.messaging()
                .producer(AtomixConstants.ResourceAllocationMessageBusName)
                .send(AtomixConstants.AllocationNotificationMessage)
                .thenAccept(w -> {
                    System.out.println("# node subscription changes acked");
                });
    }

    private boolean getIsLeader() {
        if (this.group != null && this.localMember != null) {
            return this.localMember.equals(this.group.election().term().leader());
        }

        return false;
    }
}
