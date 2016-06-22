package com.company.app.deliveryCluster.subscriptionStore;

import lombok.Data;

@Data
public class RegisteredSubscription {
    private String id;
    private String topic;
    private String uri;
    private int availCount;

    public RegisteredSubscription(final String topic, final String uri, final int availCount) {
        this.topic = topic;
        this.uri = uri;
        this.availCount = availCount;
        this.id = String.format("Subscription[%s:%s:%s]", topic, uri, availCount);
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", this.topic, this.uri);
    }
}
