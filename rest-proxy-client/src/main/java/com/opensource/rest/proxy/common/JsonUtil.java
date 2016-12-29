package com.opensource.rest.proxy.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Aaron on 26/12/2016.
 */
public abstract class JsonUtil {

    private static Gson gson = new Gson();
    private static Gson prettyGson= new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static String toPrettyJson(Object obj){
        return prettyGson.toJson(obj);
    }

    public static <T> T fromJson(String json,Class<T> clazz){
        return gson.fromJson(json,clazz);
    }
    public static <T> T fromJson(String json,Type clazz){
        return gson.fromJson(json,clazz);
    }
}
