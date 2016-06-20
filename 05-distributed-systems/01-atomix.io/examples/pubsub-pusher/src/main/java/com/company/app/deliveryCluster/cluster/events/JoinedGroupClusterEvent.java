package com.company.app.deliveryCluster.cluster.events;

import com.company.app.deliveryCluster.cluster.ClusterStateProvider;

public class JoinedGroupClusterEvent extends ClusterEvent {

    private final String memberId;

    public JoinedGroupClusterEvent(final ClusterStateProvider clusterStateProvider, final String memberId) {
        super(clusterStateProvider);
        this.memberId = memberId;
    }

    public String memberId() {
        return memberId;
    }

    @Override
    public void acceptEventVisitor(final ClusterEventVisitor visitor) {
        visitor.visit(this);
    }
}

