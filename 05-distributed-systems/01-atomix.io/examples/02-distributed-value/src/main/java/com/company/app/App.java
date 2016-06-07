package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.NettyTransport;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;
import io.atomix.variables.DistributedValue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class App
{
    // Starts the client
    public static void main( String[] args ) throws InterruptedException {
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

        AtomixReplica atomix = AtomixReplica.builder(address)
                .withTransport(new NettyTransport())
                .withStorage(Storage.builder()
                        .withStorageLevel(StorageLevel.DISK)
                        .withDirectory(args[0])
                        .withMinorCompactionInterval(Duration.ofSeconds(30))
                        .withMajorCompactionInterval(Duration.ofMinutes(1))
                        .withMaxSegmentSize(1024*1024*8)
                        .withMaxEntriesPerSegment(1024*8)
                        .build()
                ).build();

        atomix.bootstrap(cluster).join();

        atomix.<String>getValue("value").thenAccept(App::recursiveSet);

        for(;;){
            Thread.sleep(1000);
        }
    }

    /**
     * Recursively sets a value.
     */
    private static void recursiveSet(DistributedValue<String> value) {
        value.set(UUID.randomUUID().toString()).thenRun(() -> {
            value.get().thenAccept(result -> {
                value.context().schedule(Duration.ofMillis(1), () -> recursiveSet(value));
            });
        });
    }
}
