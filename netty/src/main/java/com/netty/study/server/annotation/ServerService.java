package com.netty.study.server.annotation;

import java.lang.annotation.*;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:22
 * @since 7.3.5
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ServerService {
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
}
