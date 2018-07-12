package com.json.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @Date on 2018/5/22
 * @since 6.3
 */
@UtilityClass
public class JsonUtils2 {

    public String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        return json;
    }


    public <E> E fromJson(String json, Class<E> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        E rst = mapper.readValue(json, clazz);
        return rst;
    }

    public static void main(String[] args) throws IOException {
        Map<String, Object> data = Maps.newHashMap();
        data.put("a", System.currentTimeMillis());
        data.put("b", "dksljf");
        data.put("c", 1000.2);
        data.put("d", 1000.2);
        String json=toJson(data);
        Map map = fromJson(json, Map.class);
        System.out.println(map);
    }
    @Data
    class TestData{
        @JsonProperty("a")
        Long a1;
        String b;
        int c;
    }

}
