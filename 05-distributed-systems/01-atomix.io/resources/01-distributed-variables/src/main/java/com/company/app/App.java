package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.local.LocalServerRegistry;
import io.atomix.catalyst.transport.local.LocalTransport;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;
import io.atomix.group.DistributedGroup;
import io.atomix.group.LocalMember;
import io.atomix.group.messaging.MessageConsumer;
import io.atomix.group.messaging.MessageProducer;
import io.atomix.variables.DistributedValue;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {
    private static LocalServerRegistry registry = new LocalServerRegistry();
    private static int port;

    private static Address nextAddress() {
        return new Address("localhost", port++);
    }

    private static AtomixReplica buildReplica(Address address) {
        return AtomixReplica.builder(address)
                .withTransport(new LocalTransport(registry))
                .withStorage(new Storage(StorageLevel.MEMORY))
                .build();
    }

    // node = replica
    public static void main(String[] args) throws Exception {
        // NODE 1
        Address address1 = nextAddress();
        AtomixReplica node1 = buildReplica(address1);

        node1.bootstrap().join();
        System.out.println("node1 boostrapped");

        node1.<String>getValue("test-value").thenAccept(value -> {
            value.set("Hello").thenRun(() -> {
               value.get().thenAccept(result -> {
                   System.out.println(result);
               });
            });
        });

        node1.<String>getValue("expire-value").thenAccept(value -> {
            value.set("Hello from expire-value", Duration.ofSeconds(1)).thenRun(() -> {
                value.get().thenAccept(result -> {
                    System.out.println(result);
                });
            });
        });
    }
}
/*
output:
node1 boostrapped
Hello
Hello from expire-value
 */