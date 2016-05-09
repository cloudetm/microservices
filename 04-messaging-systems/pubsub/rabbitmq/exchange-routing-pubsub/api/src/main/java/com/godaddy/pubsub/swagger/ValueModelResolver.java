package com.godaddy.pubsub.swagger;

import com.fasterxml.jackson.databind.JavaType;
import com.godaddy.pubsub.pub.Mappers;
import io.paradoxical.common.valuetypes.LongValue;
import io.paradoxical.common.valuetypes.StringValue;
import io.paradoxical.common.valuetypes.UuidValue;
import io.swagger.converter.ModelConverter;
import io.swagger.converter.ModelConverterContext;
import io.swagger.converter.ModelConverters;
import io.swagger.jackson.ModelResolver;
import io.swagger.models.properties.LongProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.StringProperty;
import io.swagger.models.properties.UUIDProperty;

import java.lang.annotation.Annotation;
import java.util.Iterator;

public class ValueModelResolver extends ModelResolver {
    /**
     * Registers the value model resolver with the swagger {@link }
     */
    public static void register() {
        ModelConverters.getInstance().addConverter(new ValueModelResolver());
    }

    public ValueModelResolver() {
        super(Mappers.json());
    }

    @Override
    public Property resolveProperty(
        final JavaType propType,
        final ModelConverterContext context,
        final Annotation[] annotations,
        final Iterator<ModelConverter> next) {

        if (propType.isTypeOrSubTypeOf(StringValue.class)) {
            return new StringProperty();
        }

        if (propType.isTypeOrSubTypeOf(LongValue.class)) {
            return new LongProperty();
        }

        if (propType.isTypeOrSubTypeOf(UuidValue.class)) {
            return new UUIDProperty();
        }

        return super.resolveProperty(propType, context, annotations, next);
    }
}