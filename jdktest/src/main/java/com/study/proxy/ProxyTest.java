package com.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/10/2
 * @creat_time: 10:31
 * @since 7.2.5
 */
public class ProxyTest {
  public static void main(String[] args) {
    Account account = (Account) Proxy.newProxyInstance(
      Account.class.getClassLoader(),
      new Class[] { Account.class },
      new SecurityProxyInvocationHandler(new AccountImpl()));
    account.getAge();
  }
  static class SecurityProxyInvocationHandler implements InvocationHandler {
    private Account proxyedObject;
    public SecurityProxyInvocationHandler(Account o) {
      proxyedObject = o;
    }
    @Override
    public Object invoke(Object object, Method method, Object[] arguments)
      throws Throwable {
      if (object instanceof Account && method.getName().equals("getAge")) {
        SecurityChecker.checkSecurity();
      }
      return method.invoke(proxyedObject, arguments);
    }
  }

  static class SecurityChecker{

    public static void checkSecurity() {
      System.out.println(".....security....");
    }
  }
}
