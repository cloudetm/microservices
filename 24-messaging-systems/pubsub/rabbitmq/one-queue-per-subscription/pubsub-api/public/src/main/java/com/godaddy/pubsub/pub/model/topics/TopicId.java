package com.godaddy.pubsub.pub.model.topics;

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
@XmlJavaTypeAdapter(value = TopicId.XmlAdapter.class)
@JsonSerialize(using = TopicId.JsonSerializeAdapter.class)
@JsonDeserialize(using = TopicId.JsonDeserializeAdapater.class)
public final class TopicId extends StringValue {
    protected TopicId(final String value) {
        super(value);
    }

    public static TopicId valueOf(final String value) {
        return new TopicId(StringUtils.trimToEmpty(value));
    }

    public static TopicId valueOf(final StringValue value) {
        return TopicId.valueOf(value.get());
    }

    public static final class XmlAdapter extends JaxbStringValueAdapter<TopicId> {
        @Override
        protected TopicId createNewInstance(final String value) {
            return TopicId.valueOf(value);
        }
    }

    public static final class JsonDeserializeAdapater extends JsonDeserializer<TopicId> {

        @Override
        public TopicId deserialize(
                final JsonParser jp,
                final DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return TopicId.valueOf(jp.getValueAsString());
        }
    }

    public static final class JsonSerializeAdapter extends JsonSerializer<TopicId> {
        @Override
        public void serialize(
                final TopicId value,
                final JsonGenerator jgen,
                final SerializerProvider provider) throws
                IOException,
                JsonProcessingException {
            jgen.writeString(value.get());
        }
    }
}
