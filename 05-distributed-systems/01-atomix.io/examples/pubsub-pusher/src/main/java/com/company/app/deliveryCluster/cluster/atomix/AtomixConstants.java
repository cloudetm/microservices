package com.company.app.deliveryCluster.cluster.atomix;

final class AtomixConstants {
    private AtomixConstants() {
    }

    public static final String GroupName = "deliveryNodes";
    public static final String SubscriptionMapName = "registeredSubscription-mapping";
    public static final String ResourceAllocationMessageBusName = "resource-allocation";

    // Move to enum or something
    public static final String AllocationNotificationMessage = "allocated";
}
