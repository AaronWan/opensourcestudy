package com.netty.study.server.codec;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:18
 * @since 7.3.5
 */
public interface ServiceBodyCodec<T> {
  byte[] encode(T body);

  T decode(byte[] data);
}
