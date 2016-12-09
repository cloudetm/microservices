package com.godaddy.pubsub.deliveryCluster.allocation;

import io.atomix.catalyst.buffer.BufferInput;
import io.atomix.catalyst.buffer.BufferOutput;
import io.atomix.catalyst.serializer.CatalystSerializable;
import io.atomix.catalyst.serializer.Serializer;
import lombok.Data;

@Data
public class AllocatedSubscription implements CatalystSerializable {
    private String nodeId;
    private String topic;
    private String uri;
    private int zone;

    public AllocatedSubscription(){
    }

    public AllocatedSubscription(final String topic, final String uri, final int zone){
        this.topic = topic;
        this.uri = uri;
        this.zone = zone;
    }

    @Override
    public void writeObject(BufferOutput<?> bufferOutput, Serializer serializer) {
        bufferOutput.writeString(topic);
        bufferOutput.writeString(uri);
        bufferOutput.writeInt(zone);
    }

    @Override
    public void readObject(BufferInput<?> bufferInput, Serializer serializer) {
        topic = bufferInput.readString();
        uri = bufferInput.readString();
        zone = bufferInput.readInt();
    }
}
