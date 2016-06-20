package com.company.app.deliveryCluster.cluster.atomix;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;

public interface AtomixClusterReplicaBuilder {
    AtomixReplica buildReplica(Address nodeAddress);
}
