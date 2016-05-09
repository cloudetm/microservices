package com.godaddy.pubsub.pub.model.subscriptions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.paradoxical.common.valuetypes.StringValue;
import io.paradoxical.common.valuetypes.adapters.xml.JaxbStringValueAdapter;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.IOException;
import java.util.UUID;

@Immutable
@XmlJavaTypeAdapter(value = SubscriptionId.XmlAdapter.class)
@JsonSerialize(using = SubscriptionId.JsonSerializeAdapter.class)
@JsonDeserialize(using = SubscriptionId.JsonDeserializeAdapater.class)
public final class SubscriptionId extends StringValue {
    protected SubscriptionId(final String value) {
        super(value);
    }

    public static SubscriptionId valueOf(String value) {
        return new SubscriptionId(StringUtils.trimToEmpty(value));
    }

    public static SubscriptionId random() {
        return SubscriptionId.valueOf(UUID.randomUUID().toString());
    }

    public static class XmlAdapter extends JaxbStringValueAdapter<SubscriptionId> {
        @Override
        protected SubscriptionId createNewInstance(String value) {
            return SubscriptionId.valueOf(value);
        }
    }

    public static class JsonDeserializeAdapater extends JsonDeserializer<SubscriptionId> {

        @Override
        public SubscriptionId deserialize(
            final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return SubscriptionId.valueOf(jp.getValueAsString());
        }
    }

    public static class JsonSerializeAdapter extends JsonSerializer<SubscriptionId> {
        @Override
        public void serialize(
                final SubscriptionId value, final JsonGenerator jgen, final SerializerProvider provider) throws
                                                                                                     IOException,
                                                                                                     JsonProcessingException {
            jgen.writeString(value.get());
        }
    }
}
