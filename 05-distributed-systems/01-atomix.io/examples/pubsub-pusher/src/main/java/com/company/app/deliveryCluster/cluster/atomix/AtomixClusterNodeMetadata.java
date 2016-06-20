package com.company.app.deliveryCluster.cluster.atomix;

import com.company.app.deliveryCluster.cluster.ClusterNodeMetadata;
import io.atomix.catalyst.buffer.BufferInput;
import io.atomix.catalyst.buffer.BufferOutput;
import io.atomix.catalyst.serializer.CatalystSerializable;
import io.atomix.catalyst.serializer.Serializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class AtomixClusterNodeMetadata implements CatalystSerializable, ClusterNodeMetadata {

    private String host;
    private int port;
    private int availabilityZone;

    @Override
    public void writeObject(final BufferOutput<?> bufferOutput, final Serializer serializer) {
        bufferOutput.writeString(host);
        bufferOutput.writeInt(port);
        bufferOutput.writeInt(availabilityZone);
    }

    @Override
    public void readObject(final BufferInput<?> bufferInput, final Serializer serializer) {
        host = bufferInput.readString();
        port = bufferInput.readInt();
        availabilityZone = bufferInput.readInt();
    }
}

