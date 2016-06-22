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

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AtomixNode {
    private static LocalServerRegistry registry = new LocalServerRegistry();
    private static int port;
    private static int memberId;

    private DistributedGroup group;
    private Worker worker;

    public AtomixNode(Worker worker){
        this.worker = worker;
    }

    private Address nextAddress() {
        return new Address("localhost", port++);
    }

    private AtomixReplica buildReplica(Address address) {
        return AtomixReplica.builder(address)
                .withTransport(new LocalTransport(registry))
                .withStorage(new Storage(StorageLevel.MEMORY))
                .build();
    }

    public void joinCluster(List<Address> cluster) throws InterruptedException, ExecutionException {
        Address address1 = nextAddress();
        cluster.add(address1);
        AtomixReplica node = buildReplica(address1);
        node.bootstrap(cluster).join();
        System.out.println("boostrapped");

        // create group
        group = node.getGroup("group").get();
        // join node into group
        LocalMember member = group.join(Integer.toString(++memberId), "foo").get();

        MessageConsumer<Object> consumer = member.messaging().consumer("topic");

        consumer.onMessage(m -> {
            System.out.println("consumer received message: '" + m.message() + "'");
            worker.doWork(m.message(), member);
        });

        group.election().onElection(term -> {
            if (term.leader().equals(member)) {
                System.out.println("member-id: "+member.id());
                System.out.println("member is leader");
            }
        });
    }

    public void broadcast(){
        MessageProducer<Object> producer = group.messaging().producer("topic");
        producer.send("hello from producer");
    }
}
