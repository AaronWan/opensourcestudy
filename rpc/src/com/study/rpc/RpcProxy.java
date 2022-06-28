package com.study.rpc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/26
 * @creat_time: 23:10
 * @since 7.5.0
 */
public class RpcProxy implements MethodInterceptor {
  public <T> T getProxyInstance(Class<T> t) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(t);
    enhancer.setCallback(this);
    return (T) enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    return methodProxy.invokeSuper(o, objects);
  }

  public static class Student implements RpcJDKProxy.Person {
    @Override
    public String study(String test) {
      return "hehe";
    }
  }


  public interface Person {
    String study(String test);
  }

  public static void main(String[] args) {
    System.out.println(new RpcProxy().getProxyInstance(Student.class).study("test"));
  }
}
