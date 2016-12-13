package com.company.app;

import io.atomix.catalyst.transport.Address;
import io.atomix.copycat.server.cluster.Member;

import java.util.ArrayList;
import java.util.List;

public class App {

    // node = replica
    public static void main(String[] args) throws Exception {

        List<Address> cluster = new ArrayList<>();

        AtomixNode atomixNode1 = new AtomixNode(new Worker());
        atomixNode1.joinCluster(cluster);
        System.out.println(cluster.toString());
        atomixNode1.broadcast();

        AtomixNode atomixNode2 = new AtomixNode(new Worker());
        atomixNode2.joinCluster(cluster);
        System.out.println(cluster.toString());
        atomixNode1.broadcast();
    }
}
/*
output:
boostrapped
member-id: 1
member is leader
[localhost/127.0.0.1:0]
consumer received message: 'hello from producer'
Worker.doWork: 'hello from producer' is from member-1
boostrapped
[localhost/127.0.0.1:0, localhost/127.0.0.1:1]
consumer received message: 'hello from producer'
Worker.doWork: 'hello from producer' is from member-1
consumer received message: 'hello from producer'
Worker.doWork: 'hello from producer' is from member-2
 */