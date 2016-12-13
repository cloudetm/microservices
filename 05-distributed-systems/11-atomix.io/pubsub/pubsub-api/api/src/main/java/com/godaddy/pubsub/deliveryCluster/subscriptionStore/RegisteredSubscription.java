package com.godaddy.pubsub.deliveryCluster.subscriptionStore;

import lombok.Data;

@Data
public class RegisteredSubscription { // in cassandra
    private String id;
    private String topic;
    private String uri;
    private int availCount;

    public RegisteredSubscription(String topic, String uri, int availCount) {
        this.topic = topic;
        this.uri = uri;
        this.availCount = availCount;
        this.id = String.format("RegisteredSubscription[%s,%s,%s]", topic, uri, availCount);
    }

    @Override
    public String toString(){
        return String.format("%s, %s", topic, uri);
    }
}
