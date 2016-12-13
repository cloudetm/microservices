package com.godaddy.pubsub.deliveryCluster.allocation;

import com.godaddy.pubsub.deliveryCluster.cluster.ClusterNodeReference;
import com.godaddy.pubsub.deliveryCluster.subscriptionStore.RegisteredSubscription;
import lombok.val;

import java.util.*;

public class SimpleSubscriptionAllocationStrategy implements SubscriptionAllocationStrategy {

    private static Map<AllocatedSubscription, ClusterNodeReference> expandRegisteredSubscriptions(
            final Collection<RegisteredSubscription> registeredSubscriptions){
        Map<AllocatedSubscription, ClusterNodeReference> subscriptionClusterNodeMap = new HashMap<>();

        registeredSubscriptions.forEach(registeredSubscription -> {
            for(int avail = 0; avail < registeredSubscription.getAvailCount(); avail++){
                AllocatedSubscription allocatedSubscription =
                        new AllocatedSubscription(
                                registeredSubscription.getTopic(),
                                registeredSubscription.getUri(),
                                avail);
                subscriptionClusterNodeMap.put(allocatedSubscription, null);
            }
        });

        return subscriptionClusterNodeMap;
    }

    private static Map<Integer, List<ClusterNodeReference>> getZoneToNodesMap(
            final Collection<ClusterNodeReference> nodes, final int numAvailZones){
        Map<Integer, List<ClusterNodeReference>> zoneToNodesMap = new HashMap<>();

        nodes.forEach(node -> {
            int zone = node.availabilityZone() % numAvailZones;

            List<ClusterNodeReference> nodesInZone = zoneToNodesMap.get(zone);

            if (nodesInZone == null) {
                nodesInZone = new ArrayList<>();
                zoneToNodesMap.put(zone, nodesInZone);
            }

            nodesInZone.add(node);
        });

        return zoneToNodesMap;
    }

    @Override
    public Collection<NodeSubscriptionChange> allocate(SubscriptionAllocationRequest subscriptionAllocationRequest) {

        Map<AllocatedSubscription, ClusterNodeReference> subscriptionToNodeMap =
                expandRegisteredSubscriptions(subscriptionAllocationRequest.getRegisteredSubscriptions());
        Map<ClusterNodeReference, Integer> nodeMetrics = new HashMap<>();
        Map<ClusterNodeReference, AllocatedSubscription> deletedSubscriptions = new HashMap<>();

        Map<Integer, List<ClusterNodeReference>> zoneToNodesMap = getZoneToNodesMap(
                subscriptionAllocationRequest.getAvailableNodes(),
                subscriptionAllocationRequest.getNumAvailabilityZone());

        zoneToNodesMap.forEach((zone, nodes) -> {

            // sort nodes by ascending metrics
            Collections.sort(nodes, (node1, node2) -> {
                int node1Metrics = nodeMetrics.getOrDefault(node1.id(), 0);
                int node2Metrics = nodeMetrics.getOrDefault(node2.id(), 0);
                if (node1Metrics == node2Metrics) {
                    return 0;
                }
                else if (node1Metrics < node2Metrics) {
                    return -1;
                }
                return 1;
            });
        });

        Collection<NodeSubscriptionChange> changes = new ArrayList<>();

        subscriptionToNodeMap.forEach((subscription, node) -> {

        });



    }
}
