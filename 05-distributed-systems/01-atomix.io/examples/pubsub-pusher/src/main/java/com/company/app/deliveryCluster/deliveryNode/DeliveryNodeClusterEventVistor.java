package com.company.app.deliveryCluster.deliveryNode;

import com.company.app.deliveryCluster.allocation.AllocatedSubscription;
import com.company.app.deliveryCluster.allocation.NodeSubscriptionChange;
import com.company.app.deliveryCluster.allocation.SubscriptionAllocationRequest;
import com.company.app.deliveryCluster.allocation.SubscriptionAllocationStrategy;
import com.company.app.deliveryCluster.cluster.ClusterNodeReference;
import com.company.app.deliveryCluster.cluster.ClusterStateProvider;
import com.company.app.deliveryCluster.cluster.events.*;
import com.company.app.deliveryCluster.subscriptionStore.RegisteredSubscription;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DeliveryNodeClusterEventVistor implements ClusterEventVisitor {
    private final SubscriptionAllocationStrategy subscriptionAllocationStrategy;

    private static final int MinNodesForAllocation = 5;
    private static final int DefaultNumAvailabilityZones = 3;

    @Inject
    public DeliveryNodeClusterEventVistor(
            final SubscriptionAllocationStrategy subscriptionAllocationStrategy) {
        this.subscriptionAllocationStrategy = subscriptionAllocationStrategy;
    }

    @Override
    public CompletableFuture<Void> visit(final AllocationRequestedClusterEvent allocationRequestedClusterEvent) {
        System.out.println("allocate by message");
        return this.allocateResources(allocationRequestedClusterEvent.getClusterStateProvider());
    }

    @Override
    public CompletableFuture<Void> visit(final DesignatedFollowerClusterEvent designatedFollowerClusterEvent) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> visit(final ElectedLeaderClusterEvent electedLeaderClusterEvent) {
        System.out.println("ElectedLeaderClusterEvent");
        //        logger.info("Leader " + this.host + ":" + this.port + ":" + this.availabilityZone);
        return this.allocateResources(electedLeaderClusterEvent.getClusterStateProvider());

    }

    @Override
    public CompletableFuture<Void> visit(final JoinedGroupClusterEvent joinedGroupClusterEvent) {

        if (joinedGroupClusterEvent.getClusterStateProvider().isLeader()) {
            return allocateResources(joinedGroupClusterEvent.getClusterStateProvider());
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> visit(final SubscriptionsChangedClusterEvent subscriptionsChangedClusterEvent) {
        System.out.println("SubscriptionsChangedClusterEvent");

        return new CompletableFuture<>();
    }

    private CompletableFuture<Collection<RegisteredSubscription>> getAllSubscriptions() {
        return null;
    }

    private CompletableFuture<Void> allocateResources(final ClusterStateProvider clusterStateProvider) {
        if (clusterStateProvider.getAvailableNodes().size() < MinNodesForAllocation) { // 5 is arbitrary
            return new CompletableFuture<>();

        }

        CompletableFuture<Collection<RegisteredSubscription>> registeredSubscriptionsFuture;
        CompletableFuture<Map<ClusterNodeReference, Collection<AllocatedSubscription>>> allocatedSubscriptionsFuture;

        registeredSubscriptionsFuture = getAllSubscriptions();
        allocatedSubscriptionsFuture = clusterStateProvider.getAllocatedSubscriptions();

        return CompletableFuture.allOf(registeredSubscriptionsFuture, allocatedSubscriptionsFuture).thenAcceptAsync(v -> {
            final Collection<RegisteredSubscription> registeredSubscriptions
                    = registeredSubscriptionsFuture.join();
            final Map<ClusterNodeReference, Collection<AllocatedSubscription>> allocatedSubscriptions
                    = allocatedSubscriptionsFuture.join();
            final Collection<ClusterNodeReference> availableNodes = clusterStateProvider.getAvailableNodes();

            try {
                Collection<NodeSubscriptionChange> changes =
                        subscriptionAllocationStrategy.allocate(
                                SubscriptionAllocationRequest.builder()
                                        .registeredSubscriptions(registeredSubscriptions)
                                        .availableNodes(availableNodes)
                                        .nodeToSubscriptionsMap(allocatedSubscriptions)
                                        .numAvailabilityZones(DefaultNumAvailabilityZones)
                                        .build());
                // Get # of Avail Zones - should get from configuration

                clusterStateProvider.applySubscriptionChanges(changes);

            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
}
