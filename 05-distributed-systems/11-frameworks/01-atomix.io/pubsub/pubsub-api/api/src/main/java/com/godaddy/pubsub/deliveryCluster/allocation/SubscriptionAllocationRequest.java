package com.godaddy.pubsub.deliveryCluster.allocation;

import com.godaddy.pubsub.deliveryCluster.cluster.ClusterNodeReference;
import com.godaddy.pubsub.deliveryCluster.subscriptionStore.RegisteredSubscription;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;
import java.util.Map;

@Value
@Builder
public class SubscriptionAllocationRequest {
    private final Collection<RegisteredSubscription> registeredSubscriptions;
    private final Map<ClusterNodeReference, Collection<AllocatedSubscription>> subscriptionNodes;
    private final Collection<ClusterNodeReference> availableNodes;
    private final int numAvailabilityZone;
}
