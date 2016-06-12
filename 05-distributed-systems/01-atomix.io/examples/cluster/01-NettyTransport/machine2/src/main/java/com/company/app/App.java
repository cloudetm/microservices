package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.netty.NettyTransport;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.group.DistributedGroup;
import io.atomix.group.LocalMember;
import io.atomix.group.messaging.MessageConsumer;
import io.atomix.group.messaging.MessageProducer;

import java.util.Arrays;
import java.util.Collection;

// NOTE: DELETE "logs" folder after each run
public class App
{
    public static void main( String[] args ) throws Exception
    {
        AtomixReplica replica2 = AtomixReplica.builder(new Address("localhost", 8704))
//                .withTransport(new LocalTransport(new LocalServerRegistry()))
                .withTransport(new NettyTransport())
                .withStorage(new Storage("logs/replica2"))
                .build();

        Collection<Address> cluster = Arrays.asList(
                new Address("localhost", 8703),
                new Address("localhost", 8704)
        );

        replica2.bootstrap(cluster).join();
        System.out.println("replica2");

        DistributedGroup group = replica2.getGroup("group").get();

        // join node into group
//        LocalMember member = group.join("node1", "metadata").get();
        LocalMember member = group.join().get();

        MessageConsumer<Object> consumer = member.messaging().consumer("topic");
        consumer.onMessage(message -> {
            System.out.println(message.message());
        });

        group.election().onElection(term -> {
           if(term.leader().equals(member)){
               System.out.println("Elected leader!");
           }
        });

        MessageProducer<Object> producer = group.messaging().producer("topic");
        producer.send("hello");
    }
}
/*
output:
replica2
hello
 */