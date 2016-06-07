package com.company.app;

import io.atomix.AtomixClient;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.NettyTransport;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        AtomixClient client = AtomixClient.builder()
                .withTransport(new NettyTransport())
                .build();

//        List<Address> cluster = Arrays.asList(
//                new Address("123.456.789.0", 8700),
//                new Address("123.456.789.1", 8700),
//                new Address("123.456.789.2", 8700)
//        );

        List<Address> cluster = Arrays.asList(
                new Address("localhost", 8700)
        );

        client.connect(cluster).thenRun(() -> {
            System.out.println("Client connected!");
        });


    }
}
