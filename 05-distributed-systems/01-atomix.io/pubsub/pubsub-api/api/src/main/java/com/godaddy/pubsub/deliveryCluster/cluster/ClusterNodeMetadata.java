package com.godaddy.pubsub.deliveryCluster.cluster;

import java.net.InetSocketAddress;

public interface ClusterNodeMetadata {
    String host();
    int port();
    int availabilityZone();

    default String id() {
        return String.format("DeliveryNode[%s:%s:%s]", host(), port(), availabilityZone());
    }

    default InetSocketAddress address(){
        return new InetSocketAddress(host(), port());
    }
}
