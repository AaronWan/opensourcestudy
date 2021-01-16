package com.netty.study.server.codec;

import com.netty.study.server.exception.RpcCodecBusinessionException;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/16
 * @creat_time: 09:47
 * @since 7.3.5
 */
public class CodecFactory {
  public enum CodeCType {
    JSON, PB
  }

  public static ServiceBodyCodec getInstance(CodeCType type) {
    switch (type) {
      case JSON:
        return JSONCodec.instance;
      default:
        throw new RpcCodecBusinessionException();
    }
  }
}
