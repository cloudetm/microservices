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

        // create group
        DistributedGroup group = node1.getGroup("group").get();

        group.onJoin(m -> System.out.println(m.id() + " joined the group"));

        // join node into group
        LocalMember member1 = group.join().get();

        MessageConsumer<Object> consumer1 = member1.messaging().consumer("topic");
        consumer1.onMessage(m -> System.out.println("consumer1: " + m.message()));

        group.election().onElection(term -> {
            if (term.leader().equals(member1)) {
                System.out.println("member1 is leader");
            }
        });

        // NODE 2
        Address address2 = nextAddress();
        AtomixReplica node2 = buildReplica(address2);

        node2.join(address1).join();
        System.out.println("node2 joined");

//        LocalMember member2 = group.join(node2).get();
        DistributedGroup group1 = node2.getGroup("group").get();
        LocalMember member2 = group1.join().get();

        MessageConsumer<Object> consumer2 = member2.messaging().consumer("topic");
        consumer2.onMessage(m -> System.out.println("consumer2: " + m.message()));

        group.election().onElection(term -> {
            if (term.leader().equals(member2)) {
                System.out.println("member2 is leader");
            }
        });

        MessageProducer<Object> producer = group.messaging().producer("topic");
        producer.send("group says hello");

        MessageProducer<Object> producer1 = member2.messaging().producer("topic");
        producer1.send("members says hello");
    }
}
/*
output:
node1 boostrapped
19c4b202-2c65-4c8d-8820-07d52c515e8b joined the group
member1 is leader
node2 joined
59928f40-bf3c-4121-9932-51a8baa9841e joined the group
consumer1: group says hello
consumer2: group says hello
consumer2: members says hello
 */
