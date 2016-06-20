package com.company.app;

import com.company.app.deliveryCluster.cluster.atomix.AtomixClusterReplicaBuilder;
import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.local.LocalServerRegistry;
import io.atomix.catalyst.transport.local.LocalTransport;
import io.atomix.copycat.server.storage.Storage;

import java.util.UUID;

public class LocalAtomixClusterReplicaBuilder implements AtomixClusterReplicaBuilder {
    private final LocalServerRegistry registry = new LocalServerRegistry();

    @Override
    public synchronized AtomixReplica buildReplica(Address nodeAddress) {
        return AtomixReplica.builder(nodeAddress)
                            .withTransport(new LocalTransport(this.registry))
                            .withStorage(Storage.builder()
                                                .withDirectory("./target/atomix-replicas/" + UUID.randomUUID().toString())
                                                .build())
                            .build();
    }
}
