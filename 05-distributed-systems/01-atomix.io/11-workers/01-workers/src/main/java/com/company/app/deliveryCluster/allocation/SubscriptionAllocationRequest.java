package com.company.app.deliveryCluster.allocation;

import com.company.app.deliveryCluster.cluster.ClusterNodeReference;
import com.company.app.deliveryCluster.subscriptionStore.RegisteredSubscription;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;
import java.util.Map;

@Value
@Builder
public class SubscriptionAllocationRequest {
    private final Collection<RegisteredSubscription> registeredSubscriptions;
    private final Map<ClusterNodeReference, Collection<AllocatedSubscription>> nodeToSubscriptionsMap;
    private final Collection<ClusterNodeReference> availableNodes;
    private final int numAvailabilityZones;
}
