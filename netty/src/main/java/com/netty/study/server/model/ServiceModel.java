package com.netty.study.server.model;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:40
 * @since 7.3.5
 */
@Data
public class ServiceModel {
  Object instance;
  Method method;
  public Object invoke(Object arg) throws InvocationTargetException, IllegalAccessException {
    return method.invoke(instance,arg);
  }
}
