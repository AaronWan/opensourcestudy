package com.json.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JacksonUtil {
    private static final ObjectMapper propertyMapper = new ObjectMapper();
    private static final ObjectMapper getterSetterMapper;

    public JacksonUtil() {
    }

    public static String toJson(Object obj) {
        try {
            return getterSetterMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static String toJson(Object obj, JsonInclude.Include include) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(include);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static String toJsonUseProperty(Object obj) {
        try {
            return propertyMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <E> E fromJson(String json, Class<E> clazz) {
        try {
            return propertyMapper.readValue(json, clazz);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <E, R> R fromJsonOfGeneric(String json, Class<E> clazz) {
        try {
            return (R) propertyMapper.readValue(json, clazz);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <E> E fromJson(String json, TypeReference<E> valueTypeRef) {
        try {
            return propertyMapper.readValue(json, valueTypeRef);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    static {
//        propertyMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//        propertyMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
//        propertyMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
//        propertyMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        propertyMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        propertyMapper.enable(new JsonGenerator.Feature[]{JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN});
//        propertyMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        getterSetterMapper = new ObjectMapper();
//        getterSetterMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//        getterSetterMapper.enable(new JsonGenerator.Feature[]{JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN});
//        getterSetterMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    }
}
