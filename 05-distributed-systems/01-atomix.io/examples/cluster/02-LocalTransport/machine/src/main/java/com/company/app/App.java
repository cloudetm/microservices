package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.local.LocalServerRegistry;
import io.atomix.catalyst.transport.local.LocalTransport;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;

import java.util.UUID;

public class App {
    private static LocalServerRegistry registry = new LocalServerRegistry();
    private static int port;

    private static Address nextAddress() {
        return new Address("localhost", port++);
    }

    public static AtomixReplica buildReplica(Address nodeAddress) {
        return AtomixReplica.builder(nodeAddress)
                .withTransport(new LocalTransport(registry))
//                .withStorage(Storage.builder()
//                        .withDirectory("logs/atomix-replicas/" + UUID.randomUUID().toString())
//                        .build())
                .withStorage(new Storage(StorageLevel.MEMORY))
                .build();
    }

    public static void main(String[] args) throws Exception {

        Address address1 = nextAddress();
        AtomixReplica node1 = buildReplica(address1);
        node1.bootstrap(address1).join();
        System.out.println("node1 bootstrapped");


        AtomixReplica node2 = buildReplica(nextAddress());
        node2.join(address1).join();
        System.out.println("node2 joined");

        System.out.println("done");
    }
}
