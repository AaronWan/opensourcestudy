package com.reflectest;

import java.lang.reflect.Proxy;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class BeanFactory{
    public static  <T>T createProxy(Class<T> proxy){
        return (T) Proxy.newProxyInstance(
                proxy.getClassLoader(),
                new Class<?>[]{proxy},
                (proxy1, method, args) -> "hehe");
    }
    public static void main(String[] args) {
        IProxyBean proxyBean=BeanFactory.createProxy(IProxyBean.class);
        System.out.println(proxyBean.toString());
    }

}
interface IProxyBean{
    String say();
}
