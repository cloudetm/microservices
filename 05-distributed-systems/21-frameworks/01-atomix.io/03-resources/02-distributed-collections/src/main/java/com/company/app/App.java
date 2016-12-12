package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.local.LocalServerRegistry;
import io.atomix.catalyst.transport.local.LocalTransport;
import io.atomix.collections.DistributedMultiMap;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;
import io.atomix.group.DistributedGroup;
import io.atomix.group.LocalMember;
import io.atomix.group.messaging.MessageConsumer;
import io.atomix.group.messaging.MessageProducer;
import io.atomix.variables.DistributedValue;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
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
//        distributedMap();
        distributedMultiMap();
    }

    private static void distributedMultiMap() {
        // NODE 1
        Address address1 = nextAddress();
        AtomixReplica node1 = buildReplica(address1);

        node1.bootstrap().join();
        System.out.println("boostrapped");

        DistributedMultiMap<String, String> multiMap = node1.<String, String>getMultiMap("multi-map").join();
        multiMap.put("foo", "item-1");
        multiMap.put("foo", "item-2");
        System.out.println("debug");
        Collection<String> values = multiMap.get("foo").join();
        System.out.println(Arrays.toString(values.toArray()));

        // thenAccept below DOES NOT WORK
//        node1.<String, String>getMultiMap("multi-map").thenAccept(map -> {
//            System.out.println("debug");
//            map.put("foo", "item-1");
//            map.put("foo", "item-2");
//
//            Collection<String> values = map.get("foo").join();
//            System.out.println(Arrays.toString(values.toArray()));
//        });
    }

    private static void distributedMap() {
        // NODE 1
        Address address1 = nextAddress();
        AtomixReplica node1 = buildReplica(address1);

        node1.bootstrap().join();
        System.out.println("boostrapped");

        node1.<String, String>getMap("map").thenAccept(map -> {
            map.put("key1", "Hello from map").thenRun(() -> {
                map.get("key1").thenAccept(result -> {
                    System.out.println(result);
                });
            });
//            map.put("key1", "item1").join();
        });
    }
}
/*
output:
node1 boostrapped
Hello
Hello from expire-value
 */