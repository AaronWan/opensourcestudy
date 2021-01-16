package com.netty.study.server.exception;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/16
 * @creat_time: 09:50
 * @since 7.3.5
 */
public class RpcRuntimeException extends RuntimeException{
  public RpcRuntimeException() {
  }

  public RpcRuntimeException(String message) {
    super(message);
  }

  public RpcRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }
  public RpcRuntimeException(Throwable cause) {
    super(cause);
  }
  public RpcRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
