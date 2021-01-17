package com.netty.study.server.exception;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/16
 * @creat_time: 09:50
 * @since 7.3.5
 */
public class RpcCodecBusinessionException extends RpcBusinessException{

  /**
   * Constructs a new runtime exception with {@code null} as its
   * detail message.  The cause is not initialized, and may subsequently be
   * initialized by a call to {@link #initCause}.
   */
  public RpcCodecBusinessionException() {
    super("10001");
  }

}
