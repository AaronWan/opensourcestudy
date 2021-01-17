package com.netty.study.server.exception;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/16
 * @creat_time: 09:50
 * @since 7.3.5
 */
public class RpcBusinessException extends RpcRuntimeException{
  /**
   * Constructs a new runtime exception with {@code null} as its
   * detail message.  The cause is not initialized, and may subsequently be
   * initialized by a call to {@link #initCause}.
   *
   * @param code
   */
  public RpcBusinessException(String code) {
    super(code);
  }

  /**
   * Constructs a new runtime exception with the specified detail message.
   * The cause is not initialized, and may subsequently be initialized by a
   * call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for
   *                later retrieval by the {@link #getMessage()} method.
   * @param code
   */
  public RpcBusinessException(String message, String code) {
    super(message, code);
  }

  /**
   * Constructs a new runtime exception with the specified detail message and
   * cause.  <p>Note that the detail message associated with
   * {@code cause} is <i>not</i> automatically incorporated in
   * this runtime exception's detail message.
   *
   * @param message the detail message (which is saved for later retrieval
   *                by the {@link #getMessage()} method).
   * @param cause   the cause (which is saved for later retrieval by the
   *                {@link #getCause()} method).  (A <tt>null</tt> value is
   *                permitted, and indicates that the cause is nonexistent or
   *                unknown.)
   * @param code
   * @since 1.4
   */
  public RpcBusinessException(String message, Throwable cause, String code) {
    super(message, cause, code);
  }

  /**
   * Constructs a new runtime exception with the specified cause and a
   * detail message of <tt>(cause==null ? null : cause.toString())</tt>
   * (which typically contains the class and detail message of
   * <tt>cause</tt>).  This constructor is useful for runtime exceptions
   * that are little more than wrappers for other throwables.
   *
   * @param cause the cause (which is saved for later retrieval by the
   *              {@link #getCause()} method).  (A <tt>null</tt> value is
   *              permitted, and indicates that the cause is nonexistent or
   *              unknown.)
   * @param code
   * @since 1.4
   */
  public RpcBusinessException(Throwable cause, String code) {
    super(cause, code);
  }

  /**
   * Constructs a new runtime exception with the specified detail
   * message, cause, suppression enabled or disabled, and writable
   * stack trace enabled or disabled.
   *
   * @param message            the detail message.
   * @param cause              the cause.  (A {@code null} value is permitted,
   *                           and indicates that the cause is nonexistent or unknown.)
   * @param enableSuppression  whether or not suppression is enabled
   *                           or disabled
   * @param writableStackTrace whether or not the stack trace should
   *                           be writable
   * @param code
   * @since 1.7
   */
  public RpcBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
    super(message, cause, enableSuppression, writableStackTrace, code);
  }
}
