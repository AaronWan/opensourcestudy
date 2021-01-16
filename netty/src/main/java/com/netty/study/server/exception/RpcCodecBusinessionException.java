package com.netty.study.server.exception;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/16
 * @creat_time: 09:50
 * @since 7.3.5
 */
public class RpcCodecBusinessionException extends RpcBusinessException{
  public RpcCodecBusinessionException() {
  }

  public RpcCodecBusinessionException(String message) {
    super(message);
  }

  public RpcCodecBusinessionException(String message, Throwable cause) {
    super(message, cause);
  }
  public RpcCodecBusinessionException(Throwable cause) {
    super(cause);
  }
  public RpcCodecBusinessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
