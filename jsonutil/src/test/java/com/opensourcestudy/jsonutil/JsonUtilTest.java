package com.opensourcestudy.jsonutil;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Aaron on 15/05/2017.
 */
public class JsonUtilTest {
    public static final Gson gson = new GsonBuilder().create();

    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Map<String, Object> data = Maps.newLinkedHashMap();
        data.put("test1", 200.0);
        data.put("test2", 200);
        Map<String, Object> data1 = Maps.newLinkedHashMap();
        data1.put("test1", 200.0);
        data1.put("test2", 200);
        data.put("data", data1);
        String json = gson.toJson(data);
        System.out.println("gson:" + gson.fromJson(json, LinkedHashMap.class));
        System.out.println("jackson:" + mapper.readValue(json.getBytes(), HashMap.class));
        System.out.println("fastjson:" + JSON.parseObject(json, HashMap.class));
        long t1 = System.currentTimeMillis();
        int n = 100000;
        for (int i = 0; i < n; i++) {
            gson.fromJson(json, LinkedHashMap.class);
        }
        System.out.println("gson" + (System.currentTimeMillis() - t1));


        t1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            mapper.readValue(json.getBytes(), HashMap.class);
        }

        System.out.println("jackson" + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            JSON.parseObject(json, HashMap.class);
        }
        System.out.println("fastjson JSON" + (System.currentTimeMillis() - t1));
    }

    @Test
    public void testTypeBean() {
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(BaseType.class, new TypeAdapter<BaseType>() {
            @Override
            public void write(JsonWriter out, BaseType value) throws IOException {
                switch (value.type){
                    case parall:
                        BaseType1 baseType1= (BaseType1) value;
                        out.beginObject()
                        .name("type").value(baseType1.getType().name())
                        .name("name").value(baseType1.getName()).endObject();

                        break;
                    case userTask:
                        BaseType2 baseType2= (BaseType2) value;
                        out.name("type").value(baseType2.getType().name());
                        out.name("name2").value(baseType2.getName2());
                        break;
                }
                return;
            }

            @Override
            public BaseType read(JsonReader in) throws IOException {
                return null;
            }
        }).create();


        BaseType1 baseType = new BaseType1();
        baseType.setType(ClassType.parall);
        baseType.setName("test");
        BaseType type=gson.fromJson(gson.toJson(baseType),BaseType.class);
        System.out.println(JSON.toJSON(type));

    }
@Data
    class BaseType {
        private ClassType type;
    }
@Data
    class BaseType1 extends BaseType {
        private String name;
    }
@Data
    class BaseType2 extends BaseType {
        private String name2;
    }

    enum ClassType {
        userTask, parall;
    }
}
