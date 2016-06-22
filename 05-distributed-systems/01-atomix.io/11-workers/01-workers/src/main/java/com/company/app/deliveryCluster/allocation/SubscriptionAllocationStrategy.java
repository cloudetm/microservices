package com.company.app.deliveryCluster.allocation;

import com.company.app.deliveryCluster.cluster.ClusterNodeReference;
import com.company.app.deliveryCluster.subscriptionStore.RegisteredSubscription;
import lombok.val;

import java.util.*;

public class SubscriptionAllocationStrategy {

    private static Map<AllocatedSubscription, ClusterNodeReference>
    expandRegisteredSubscriptions(final Collection<RegisteredSubscription> registeredSubscriptions) {

        HashMap<AllocatedSubscription, ClusterNodeReference> subscriptionNodeMap = new HashMap<>();

        registeredSubscriptions.forEach(registeredSubscription -> {
            for (int avail = 0; avail < registeredSubscription.getAvailCount(); ++avail) {
                AllocatedSubscription allocatedSubscription = new AllocatedSubscription(registeredSubscription.getTopic(),
                        registeredSubscription.getUri(), avail);
                subscriptionNodeMap.put(allocatedSubscription, null);
            }
        });
        return subscriptionNodeMap;
    }

    private static Map<Integer, List<ClusterNodeReference>>
    getZoneToNodesMap(final Collection<ClusterNodeReference> nodes, final int numAvailabilityZones) {

        HashMap<Integer, List<ClusterNodeReference>> zoneToNodesMap = new HashMap<>();

        nodes.forEach(node -> {
            int zone = node.availabilityZone() % numAvailabilityZones;

            List<ClusterNodeReference> nodesInZone = zoneToNodesMap.get(zone);

            if (nodesInZone == null) {
                nodesInZone = new ArrayList<>();
                zoneToNodesMap.put(zone, nodesInZone);
            }

            nodesInZone.add(node);
        });

        return zoneToNodesMap;
    }

    public Collection<NodeSubscriptionChange> allocate(final SubscriptionAllocationRequest subscriptionAllocationRequest) {

        val subscriptionToNodeMap = expandRegisteredSubscriptions(subscriptionAllocationRequest.getRegisteredSubscriptions());


        HashMap<ClusterNodeReference, Integer> nodeMetrics = new HashMap<>();
        HashMap<ClusterNodeReference, AllocatedSubscription> deletedSubscriptions = new HashMap<>();

        // Map allocated subscriptions to target subscriptions,
        // returning count of active scubscription per node and list of deleted subscriptions
        subscriptionAllocationRequest.getNodeToSubscriptionsMap().forEach((node, subscriptions) -> {
            subscriptions.forEach(subscription -> {
                if (subscriptionToNodeMap.containsKey(subscription)) {

                    // add scubscription not assigned to a node
                    subscriptionToNodeMap.put(subscription, node);
                    nodeMetrics.put(node, nodeMetrics.getOrDefault(node, 0) + 1);
                    // Count number of subscriptions per node
                }
                else {
                    // remove allocated subscription no longer needed
                    deletedSubscriptions.put(node, subscription);
                }
            });
        });

        // put nodes into availablity zones
        Map<Integer, List<ClusterNodeReference>> zoneToNodesMap = getZoneToNodesMap(subscriptionAllocationRequest.getAvailableNodes(),
                subscriptionAllocationRequest.getNumAvailabilityZones());

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

        // For all subscriptions without nodes allocated
        subscriptionToNodeMap.forEach((subscription, node) -> {
            if (node == null) {

                // allocate the subscription to the next node in the availability zone
                int zone = subscription.getZone() % subscriptionAllocationRequest.getNumAvailabilityZones();
                List<ClusterNodeReference> nodesInZone = zoneToNodesMap.get(zone);

                if (nodesInZone == null || nodesInZone.isEmpty()) {
                    return;
                }

                ClusterNodeReference newNode = nodesInZone.get(0);

                // Rotate to next node in the zone
                Collections.rotate(nodesInZone, 1);

                changes.add(new NodeSubscriptionChange(NodeSubscriptionChangeType.SubscriptionAdded, newNode, subscription));
            }
        });

        // Remove subscriptions no longer needed
        deletedSubscriptions.forEach((node, subscription) -> {
            changes.add(new NodeSubscriptionChange(NodeSubscriptionChangeType.SubscriptionRemoved, node, subscription));
        });

        return changes;
    }

}
