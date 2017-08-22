package com.opensource;

import com.google.common.collect.Maps;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.Tag;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.Test;

import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testProto() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "sjdklfj");
        map.put("age", 120);
        Person p = new Person();
        p.setName("test");
        p.setProperties(map);
        Object ret = getMap(Person.class,p);
        System.out.println(ret);
    }

    private <T>T getMap(Class<T> mapT,T map) {
        Schema<T> schema = RuntimeSchema.getSchema(mapT);
        byte[] bytes = ProtobufIOUtil.toByteArray(map, schema, allocLinkedBuffer());
        Schema<T> schema1 = RuntimeSchema.getSchema(mapT);
        T ret = schema1.newMessage();
        ProtobufIOUtil.mergeFrom(bytes, ret, schema);
        return ret;
    }

    private static LinkedBuffer allocLinkedBuffer() {
        return LinkedBuffer.allocate(2048);
    }

}
