package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.local.LocalServerRegistry;
import io.atomix.catalyst.transport.local.LocalTransport;
import io.atomix.catalyst.transport.netty.NettyTransport;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;
import io.atomix.group.DistributedGroup;
import io.atomix.group.LocalMember;
import io.atomix.group.messaging.MessageConsumer;
import io.atomix.group.messaging.MessageProducer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        forLoop();
        oneByOne();
    }

    private static void forLoop() {
        System.out.println("#forLoop");
        List<Address> cluster = new ArrayList<>();

        for(int i = 0; i < 2; i++){
            Address address = nextAddress();
            cluster.add(address);
            AtomixReplica node = buildReplica(address);
            node.bootstrap(cluster).join();
            System.out.println("bootstrapped");
        }

        System.out.println(Arrays.toString(cluster.toArray()));
    }

    private static void oneByOne() throws InterruptedException, ExecutionException {
        System.out.println("\n#oneByOne");

        // NODE 1
        List<Address> cluster = new ArrayList<>();

        Address address1 = nextAddress();
        cluster.add(address1);
        AtomixReplica node1 = buildReplica(address1);
        node1.bootstrap(cluster).join();
        System.out.println("node1 boostrapped");

        DistributedGroup group1 = node1.getGroup("group").get();
        LocalMember member1 = group1.join().get();

        MessageConsumer<Object> consumer1 = member1.messaging().consumer("topic");
        consumer1.onMessage(m -> System.out.println("consumer1: " + m.message()));

        group1.election().onElection(term -> {
            if (term.leader().equals(member1)) {
                System.out.println("member1 is leader");
            }
        });

        // NODE 2
        Address address2 = nextAddress();
        cluster.add(address2);
        AtomixReplica node2 = buildReplica(address2);
        node2.bootstrap(cluster).join();
        System.out.println("node2 boostrapped");

        DistributedGroup group2 = node2.getGroup("group").get();
        LocalMember member2 = group2.join().get();

        MessageConsumer<Object> consumer2 = member2.messaging().consumer("topic");
        consumer2.onMessage(m -> System.out.println("consumer2: " + m.message()));

        group2.election().onElection(term -> {
            if (term.leader().equals(member2)) {
                System.out.println("member2 is leader");
            }
        });

        MessageProducer<Object> producer = group1.messaging().producer("topic");
        producer.send("hello");
    }
}
/*
output:
#forLoop
bootstrapped
bootstrapped
[localhost/127.0.0.1:0, localhost/127.0.0.1:1]

#oneByOne
node1 boostrapped
member1 is leader
node2 boostrapped
consumer1: hello
consumer2: hello
 */