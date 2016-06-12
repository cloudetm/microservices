package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.LocalServerRegistry;
import io.atomix.catalyst.transport.LocalTransport;
import io.atomix.catalyst.transport.NettyTransport;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.group.DistributedGroup;
import io.atomix.group.LocalMember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class App {

    /**
     * Starts the server.
     */
    public static void main(String[] args) throws Exception {
//        old(args);

        AtomixReplica replica1 = AtomixReplica.builder(new Address("123.456.789.0", 8700))
                .withTransport(new NettyTransport())
                .withStorage(new Storage("logs/replica1"))
                .build();

        Collection<Address> cluster = Arrays.asList(
                new Address("123.456.789.0", 8700)
        );

        replica1.bootstrap(cluster).thenRun(() -> {
            System.out.println("hi");
        });
    }

    private static void old(String[] args) throws InterruptedException, java.util.concurrent.ExecutionException {
        if (args.length < 2)
            throw new IllegalArgumentException("must supply a path and set of host:port tuples");

        // Parse the address to which to bind the server.
        String[] mainParts = args[1].split(":");
        Address address = new Address(mainParts[0], Integer.valueOf(mainParts[1]));

        // Build a list of all member addresses to which to connect.
        List<Address> cluster = new ArrayList<>();
//        cluster.add(address);
        for (int i = 1; i < args.length; i++) {
            String[] parts = args[i].split(":");
            cluster.add(new Address(parts[0], Integer.valueOf(parts[1])));
        }

        // Create a stateful Atomix replica. The replica communicates with other replicas in the cluster
        // to replicate state changes.
        AtomixReplica replica = AtomixReplica.builder(address)
                .withTransport(new LocalTransport(new LocalServerRegistry()))
//                .withTransport(new NettyTransport())
                .withStorage(new Storage(args[0]))
                .build();

        // Open the replica. Once this operation completes resources can be created and managed.
        replica.bootstrap(cluster).join();

        System.out.println("HERE: " + args[0]);

        // Create a leader election resource.
        DistributedGroup group = replica.getGroup("group").get();

        // Join the group.
        LocalMember member = group.join().get();

        // Register a callback to be called when the local member is elected the leader.
        group.election().onElection(term -> {
            if (term.leader().equals(member)) {
                System.out.println("Elected leader!");
            }
        });

        // Block while the replica is open.
        for (;;) {
            Thread.sleep(1000);
        }
    }
}