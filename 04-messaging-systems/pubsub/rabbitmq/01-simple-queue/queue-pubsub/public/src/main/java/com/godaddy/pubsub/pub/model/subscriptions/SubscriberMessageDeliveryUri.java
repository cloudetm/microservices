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

@Immutable
@XmlJavaTypeAdapter(value = SubscriberMessageDeliveryUri.XmlAdapter.class)
@JsonSerialize(using = SubscriberMessageDeliveryUri.JsonSerializeAdapter.class)
@JsonDeserialize(using = SubscriberMessageDeliveryUri.JsonDeserializeAdapater.class)
public final class SubscriberMessageDeliveryUri extends StringValue {
    protected SubscriberMessageDeliveryUri(final String value) {
        super(value);
    }

    public static SubscriberMessageDeliveryUri valueOf(String value) {
        return new SubscriberMessageDeliveryUri(StringUtils.trimToEmpty(value));
    }

    public static class XmlAdapter extends JaxbStringValueAdapter<SubscriberMessageDeliveryUri> {
        @Override
        protected SubscriberMessageDeliveryUri createNewInstance(String value) {
            return SubscriberMessageDeliveryUri.valueOf(value);
        }
    }

    public static class JsonDeserializeAdapater extends JsonDeserializer<SubscriberMessageDeliveryUri> {

        @Override
        public SubscriberMessageDeliveryUri deserialize(
            final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return SubscriberMessageDeliveryUri.valueOf(jp.getValueAsString());
        }
    }

    public static class JsonSerializeAdapter extends JsonSerializer<SubscriberMessageDeliveryUri> {
        @Override
        public void serialize(
                final SubscriberMessageDeliveryUri value, final JsonGenerator jgen, final SerializerProvider provider) throws
                                                                                                                   IOException,
                                                                                                                   JsonProcessingException {
            jgen.writeString(value.get());
        }
    }
}
