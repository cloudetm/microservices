package com.company.app;

import io.atomix.catalyst.transport.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        List<Address> cluster = new ArrayList<>();

        ClusterNodeMetadata metadata = new ClusterNodeMetadata("id1", "name1");



        for(int i = 0; i < 2; i++) {
            AtomixClusterController controller = new AtomixClusterController(
                    cluster,
                    metadata,
                    new DefaultClusterEventHandler(
                            new DeliveryNodeClusterEventVisitor()));

            Thread thread = new Thread(() -> {
                try {
                    controller.joinCluster();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            thread.join();
        }
        System.out.println(Arrays.toString(cluster.toArray()));
    }
}
/*
output:
bootstrapped
bootstrapped
DeliveryNodeClusterEventVisitor received following:
  joined group
  ClusterNodeMetadata(id=id1, name=name1)
[localhost/127.0.0.1:0, localhost/127.0.0.1:1]
 */