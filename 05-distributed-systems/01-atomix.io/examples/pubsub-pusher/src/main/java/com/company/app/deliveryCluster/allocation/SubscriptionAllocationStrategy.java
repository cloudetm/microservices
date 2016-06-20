package com.company.app.deliveryCluster.allocation;


import java.util.Collection;

public interface SubscriptionAllocationStrategy {
    Collection<NodeSubscriptionChange> allocate(final SubscriptionAllocationRequest allocationRequest);
}
