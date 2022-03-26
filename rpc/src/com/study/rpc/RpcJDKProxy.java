package com.study.rpc;

import java.lang.reflect.Proxy;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/26
 * @creat_time: 23:10
 * @since 7.5.0
 */
public class RpcJDKProxy {
  private Object target;

  public RpcJDKProxy(Object target) {
    this.target = target;
  }

  public Object getProxyInstance(){
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
      return method.invoke(target, args);
    });
  }

  public static class Student implements Person{
    @Override
     public String study(String test){
        return "hehe";
     }
  }

  public interface Person{
    String study(String test);
  }

  public static void main(String[] args) {
    Person student=((Person)new RpcJDKProxy(new Student()).getProxyInstance());
    System.out.println(student.study("test"));
  }
}
