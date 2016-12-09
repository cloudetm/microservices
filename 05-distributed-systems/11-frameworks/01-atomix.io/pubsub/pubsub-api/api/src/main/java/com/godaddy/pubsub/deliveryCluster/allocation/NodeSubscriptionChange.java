package com.godaddy.pubsub.deliveryCluster.allocation;

import com.godaddy.pubsub.deliveryCluster.cluster.ClusterNodeReference;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NodeSubscriptionChange {
    private final NodeSubscriptionChangeType changeType;
    private final ClusterNodeReference node;
    private final AllocatedSubscription subscription;

}
