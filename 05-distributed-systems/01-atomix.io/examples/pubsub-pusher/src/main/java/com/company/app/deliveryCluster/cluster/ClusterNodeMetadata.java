package com.company.app.deliveryCluster.cluster;

import java.net.InetSocketAddress;

public interface ClusterNodeMetadata {
    default String id() {
        return String.format("DeliveryNode[%s:%s:%s]", host(), port(), availabilityZone());
    }

    int availabilityZone();

    String host();

    int port();

    default InetSocketAddress address() {
        return new InetSocketAddress(host(), port());
    }
}
