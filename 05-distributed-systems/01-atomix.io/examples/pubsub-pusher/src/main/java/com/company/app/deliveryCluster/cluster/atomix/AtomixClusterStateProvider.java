package com.company.app.deliveryCluster.cluster.atomix;

import com.company.app.deliveryCluster.allocation.AllocatedSubscription;
import com.company.app.deliveryCluster.allocation.NodeSubscriptionChange;
import com.company.app.deliveryCluster.cluster.ClusterNodeReference;
import com.company.app.deliveryCluster.cluster.ClusterStateProvider;
import com.company.app.deliveryCluster.cluster.NodeSubscriptionChangeBuffer;
import io.atomix.collections.DistributedMultiMap;
import io.atomix.group.GroupMember;
import lombok.val;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AtomixClusterStateProvider implements ClusterStateProvider {

    private boolean isLeader;
    private final Collection<GroupMember> currentGroupMembers;
    private final DistributedMultiMap<String, AllocatedSubscription> nodeToSubscriptionMap;
    private final NodeSubscriptionChangeBuffer changeBuffer;

    public AtomixClusterStateProvider(
            final boolean isLeader,
            final Collection<GroupMember> currentGroupMembers,
            final DistributedMultiMap<String, AllocatedSubscription> nodeToSubscriptionMap,
            final NodeSubscriptionChangeBuffer changeBuffer) {

        this.isLeader = isLeader;
        this.currentGroupMembers = currentGroupMembers;
        this.nodeToSubscriptionMap = nodeToSubscriptionMap;
        this.changeBuffer = changeBuffer;
    }

    @Override
    public CompletableFuture<Map<ClusterNodeReference, Collection<AllocatedSubscription>>> getAllocatedSubscriptions() {

        val nodeSubscriptionFutures = new HashMap<GroupMember, CompletableFuture<Collection<AllocatedSubscription>>>();

        // Kick off getting the subscriptions from the distributed map
        currentGroupMembers.forEach(member -> {
            nodeSubscriptionFutures.put(member, this.nodeToSubscriptionMap.get(member.id()));
        });

        // Wait for all the gets be completed
        final CompletableFuture[] completableFutures =
                nodeSubscriptionFutures.values()
                        .toArray(new CompletableFuture[nodeSubscriptionFutures.size()]);

        val allDoneFuture = CompletableFuture.allOf(completableFutures);

        return allDoneFuture.thenApply((v) -> {
            Map<ClusterNodeReference, Collection<AllocatedSubscription>> nodeSubscriptions = new HashMap<>();

            nodeSubscriptionFutures.forEach((member, future) -> {
                Collection<AllocatedSubscription> subscriptions = future.join();
                nodeSubscriptions.put(new AtomixClusterNodeReference(member), subscriptions);
            });

            return nodeSubscriptions;
        });
    }

    @Override
    public Collection<ClusterNodeReference> getAvailableNodes() {
        try {

            return currentGroupMembers.stream()
                    .map(member -> (ClusterNodeReference) new AtomixClusterNodeReference(member))
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void applySubscriptionChanges(final Collection<NodeSubscriptionChange> changes) {
        this.changeBuffer.accumelate(changes);
    }

    @Override
    public boolean isLeader() {
        return isLeader;
    }
}
