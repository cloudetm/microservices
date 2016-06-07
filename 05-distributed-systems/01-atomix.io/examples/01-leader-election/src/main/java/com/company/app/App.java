package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.NettyTransport;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.group.DistributedGroup;
import io.atomix.group.LocalMember;

import java.util.ArrayList;
import java.util.List;


public class App {

    /**
     * Starts the server.
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2)
            throw new IllegalArgumentException("must supply a path and set of host:port tuples");

        // Parse the address to which to bind the server.
        String[] mainParts = args[1].split(":");
        Address address = new Address(mainParts[0], Integer.valueOf(mainParts[1]));

        // Build a list of all member addresses to which to connect.
        List<Address> cluster = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            String[] parts = args[i].split(":");
            cluster.add(new Address(parts[0], Integer.valueOf(parts[1])));
        }

        // Create a stateful Atomix replica. The replica communicates with other replicas in the cluster
        // to replicate state changes.
        AtomixReplica atomix = AtomixReplica.builder(address)
                .withTransport(new NettyTransport())
                .withStorage(new Storage(args[0]))
                .build();

        // Open the replica. Once this operation completes resources can be created and managed.
        atomix.bootstrap(cluster).join();

        System.out.println("HERE: " + args[0]);

        // Create a leader election resource.
        DistributedGroup group = atomix.getGroup("group").get();

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