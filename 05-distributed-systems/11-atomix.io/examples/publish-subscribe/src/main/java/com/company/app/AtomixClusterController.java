package com.company.app;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.local.LocalServerRegistry;
import io.atomix.catalyst.transport.local.LocalTransport;
import io.atomix.collections.DistributedMultiMap;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;
import io.atomix.group.DistributedGroup;
import io.atomix.group.LocalMember;

import java.util.List;

interface ClusterStateProvider{
    ClusterNodeMetadata getMetadata();
}
class AtomixClusterStateProvider implements ClusterStateProvider{
    private ClusterNodeMetadata clusterNodeMetadata;
    public AtomixClusterStateProvider(ClusterNodeMetadata clusterNodeMetadata){
        this.clusterNodeMetadata = clusterNodeMetadata;
    }
    @Override
    public ClusterNodeMetadata getMetadata() {
        return clusterNodeMetadata;
    }
}
interface ClusterEventHandler{
    void onClusterEvent(ClusterEvent clusterEvent);
}
class DefaultClusterEventHandler implements ClusterEventHandler{
    private ClusterEventVisitor clusterEventVisitor;
    public DefaultClusterEventHandler(ClusterEventVisitor clusterEventVisitor){
        this.clusterEventVisitor = clusterEventVisitor;
    }
    @Override
    public void onClusterEvent(ClusterEvent clusterEvent) {
        clusterEvent.accept(clusterEventVisitor);
    }
}
interface ClusterEventVisitor{
    void visit(JoinedGroupClusterEvent clusterEvent);
}
class DeliveryNodeClusterEventVisitor implements ClusterEventVisitor{
    @Override
    public void visit(JoinedGroupClusterEvent clusterEvent) {
        System.out.println("DeliveryNodeClusterEventVisitor received following:");
        System.out.println("  joined group");

        System.out.println("  "+clusterEvent.getClusterStateProvider().getMetadata());
    }
}
abstract class ClusterEvent{
    private ClusterStateProvider clusterStateProvider;
    public ClusterEvent(ClusterStateProvider clusterStateProvider){
        this.clusterStateProvider = clusterStateProvider;
    }
    public ClusterStateProvider getClusterStateProvider(){
        return this.clusterStateProvider;
    }
    public abstract void accept(ClusterEventVisitor clusterEventVisitor);
}
class JoinedGroupClusterEvent extends ClusterEvent{

    public JoinedGroupClusterEvent(ClusterStateProvider clusterStateProvider) {
        super(clusterStateProvider);
    }

    @Override
    public void accept(ClusterEventVisitor clusterEventVisitor) {
        clusterEventVisitor.visit(this);
    }
}
public class AtomixClusterController {
    private LocalServerRegistry registry = new LocalServerRegistry();
    private static int port;

    private List<Address> cluster;
    private ClusterNodeMetadata clusterNodeMetadata;
    private ClusterEventHandler clusterEventHandler;

    private DistributedMultiMap<String, String> multiMap;

    public AtomixClusterController(
            List<Address> cluster,
            ClusterNodeMetadata clusterNodeMetadata,
            ClusterEventHandler clusterEventHandler) {
        this.cluster = cluster;
        this.clusterNodeMetadata = clusterNodeMetadata;
        this.clusterEventHandler = clusterEventHandler;
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

    // node = replica
    public synchronized void joinCluster() throws Exception {
        Address address = nextAddress();
        this.cluster.add(address);
        AtomixReplica node = buildReplica(address);
        node.bootstrap(cluster).join();
        System.out.println("bootstrapped");

        DistributedGroup group = node.getGroup("group").get();
        LocalMember member = group.join(clusterNodeMetadata.getId(), clusterNodeMetadata).get();
        this.multiMap = node.<String, String>getMultiMap("multiMap").join();
        group.onJoin(m -> {
            clusterEventHandler.onClusterEvent(
                    new JoinedGroupClusterEvent(
                            new AtomixClusterStateProvider(clusterNodeMetadata)));
        });
    }
}
