package com.godaddy.pubsub.deliveryCluster.cluster.atomix;

import com.godaddy.pubsub.deliveryCluster.cluster.ClusterNodeReference;
import com.godaddy.pubsub.deliveryCluster.cluster.ClusterNodeMetadata;
import io.atomix.group.GroupMember;

public class AtomixClusterNodeReference implements ClusterNodeReference {
    private final GroupMember member;

    public AtomixClusterNodeReference(final GroupMember member) {
        this.member = member;
    }

    @Override
    public String id() {
        return member.id();
    }

    @Override
    public int availabilityZone() {
        final ClusterNodeMetadata clusterNodeMetadata = (ClusterNodeMetadata)this.member.metadata().get();

        return clusterNodeMetadata.availabilityZone();
    }

    @Override
    public String toString() {
        return String.format("{%s:%s}", this.id(), availabilityZone());
    }
}
