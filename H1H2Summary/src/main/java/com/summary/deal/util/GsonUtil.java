package com.summary.deal.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import lombok.experimental.UtilityClass;

/**
 * @author wansong
 * @date 2023/7/9
 * @apiNote
 **/
@UtilityClass
public class GsonUtil {
    private static Gson gson = new GsonBuilder().create();

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }
}
