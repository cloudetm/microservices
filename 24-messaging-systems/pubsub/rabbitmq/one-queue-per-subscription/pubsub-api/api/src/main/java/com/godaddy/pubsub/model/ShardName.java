package com.godaddy.pubsub.model;

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

@Immutable
@XmlJavaTypeAdapter(value = ShardName.XmlAdapter.class)
@JsonSerialize(using = ShardName.JsonSerializeAdapter.class)
@JsonDeserialize(using = ShardName.JsonDeserializeAdapater.class)
public final class ShardName extends StringValue {
    protected ShardName(final String value) {
        super(value);
    }

    public static ShardName valueOf(final String value) {
        return new ShardName(StringUtils.trimToEmpty(value));
    }

    public static ShardName valueOf(final StringValue value) {
        return ShardName.valueOf(value.get());
    }

    public static final class XmlAdapter extends JaxbStringValueAdapter<ShardName> {
        @Override
        protected ShardName createNewInstance(final String value) {
            return ShardName.valueOf(value);
        }
    }

    public static final class JsonDeserializeAdapater extends JsonDeserializer<ShardName> {

        @Override
        public ShardName deserialize(
                final JsonParser jp,
                final DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return ShardName.valueOf(jp.getValueAsString());
        }
    }

    public static final class JsonSerializeAdapter extends JsonSerializer<ShardName> {
        @Override
        public void serialize(
                final ShardName value,
                final JsonGenerator jgen,
                final SerializerProvider provider) throws
                IOException,
                JsonProcessingException {
            jgen.writeString(value.get());
        }
    }
}
