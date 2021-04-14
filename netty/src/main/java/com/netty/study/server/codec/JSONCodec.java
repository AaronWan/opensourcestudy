package com.netty.study.server.codec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.charset.StandardCharsets;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:18
 * @since 7.3.5
 */
public class JSONCodec implements ServiceBodyCodec {

  public static final Gson gson = new GsonBuilder().create();
  protected static JSONCodec instance=new JSONCodec();
  private JSONCodec(){}

  @Override
  public byte[] encode(Object body) {
    return gson.toJson(body).getBytes(StandardCharsets.UTF_8);
  }

  @Override
  public <T> T decode(byte[] data, Class<T> clazz) {
    return gson.fromJson(new String(data), clazz);
  }
}
