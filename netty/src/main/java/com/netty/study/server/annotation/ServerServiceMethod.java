package com.netty.study.server.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:22
 * @since 7.3.5
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ServerServiceMethod {
  /**
   * service name
   * @return
   */
  String value();

  /**
   * service name
   * @return
   */
  String desc() default "no desc";

  /**
   * service interface version
   * @return
   */
  String version() default "v1.0";
}
