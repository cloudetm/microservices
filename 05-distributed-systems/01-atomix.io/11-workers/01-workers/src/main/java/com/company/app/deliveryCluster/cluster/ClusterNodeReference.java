package com.company.app.deliveryCluster.cluster;

import io.atomix.group.GroupMember;

public class ClusterNodeReference {

    private final GroupMember member;

    public ClusterNodeReference(final GroupMember member) {
        this.member = member;
    }

    public String id() {
        return this.member.id();
    }

    public int availabilityZone() {
        final ClusterNodeMetadata clusterNodeMetadata = (ClusterNodeMetadata) this.member.metadata().get();

        return clusterNodeMetadata.availabilityZone();
    }

    @Override
    public String toString() {
        return String.format("{%s:%s}", this.id(), availabilityZone());
    }
}
