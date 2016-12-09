package com.company.app.deliveryCluster.allocation;

import io.atomix.catalyst.buffer.BufferInput;
import io.atomix.catalyst.buffer.BufferOutput;
import io.atomix.catalyst.serializer.CatalystSerializable;
import io.atomix.catalyst.serializer.Serializer;
import lombok.Data;

@Data
public class AllocatedSubscription implements CatalystSerializable {
    private String topic;
    private String uri;
    private int zone;

    public AllocatedSubscription(final String topic, final String uri, final int zone) {
        this.topic = topic;
        this.uri = uri;
        this.zone = zone;
    }

    @Override
    public void writeObject(final BufferOutput<?> bufferOutput, final Serializer serializer) {
        bufferOutput.writeString(this.topic);
        bufferOutput.writeString(this.uri);
        bufferOutput.writeInt(this.zone);

    }

    @Override
    public void readObject(final BufferInput<?> bufferInput, final Serializer serializer) {
        this.topic = bufferInput.readString();
        this.uri = bufferInput.readString();
        this.zone = bufferInput.readInt();
    }
}
