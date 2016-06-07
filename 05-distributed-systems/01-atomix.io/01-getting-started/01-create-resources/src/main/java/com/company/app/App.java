package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.NettyTransport;
import io.atomix.concurrent.DistributedLock;
import io.atomix.copycat.server.storage.Storage;

import java.util.concurrent.CompletableFuture;

public class App
{
    public static void main( String[] args )
    {
        Address address1 = new Address("localhost", 8700);
        AtomixReplica replica = AtomixReplica.builder(address1)
                .withTransport(new NettyTransport())
                .withStorage(new Storage("logs/server1"))
                .build();

        CompletableFuture<AtomixReplica> future = replica.bootstrap();
        future.join(); // block until the replica is bootstrapped

        Address address2 = new Address("localhost", 8701);
        AtomixReplica replica2 = AtomixReplica.builder(address2)
                .withTransport(new NettyTransport())
                .withStorage(new Storage("logs/server2"))
                .build();

        replica2.join(address1).join();

        DistributedLock lock = replica.getLock("my-lock").join();
        lock.lock().thenRun(() -> System.out.println("Acquired a lock!"));
    }
}
/*
output: sometimes
Acquired a lock!
 */