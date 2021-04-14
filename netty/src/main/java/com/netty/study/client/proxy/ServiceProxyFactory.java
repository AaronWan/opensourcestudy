package com.netty.study.client.proxy;

import com.google.common.reflect.Reflection;
import com.netty.study.client.NettyClient;
import com.netty.study.server.annotation.ServerService;
import com.netty.study.server.annotation.ServerServiceMethod;
import com.netty.study.server.codec.CodecFactory;

import java.lang.reflect.Method;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/16
 * @creat_time: 21:54
 * @since 7.3.5
 */
public class ServiceProxyFactory {

  private NettyClient client;

  public ServiceProxyFactory(String ip,int port) {
    client=new NettyClient(ip,port);
  }

  public <T> T newRestServiceProxy(Class<T> clazz) {
    return Reflection.newProxy(clazz, (Object proxy, Method method, Object[] args) -> {
      ServerService restResource = method.getDeclaringClass().getAnnotation(ServerService.class);
      ServerServiceMethod methodAnn = method.getDeclaredAnnotation(ServerServiceMethod.class);
      String serviceUrl = restResource.value() + "." + methodAnn.value();
      try {
        byte[] rst = client.getChannel().sendMessage(serviceUrl, CodecFactory.getInstance(CodecFactory.CodeCType.JSON).encode(args[0]));
        return CodecFactory.getInstance(CodecFactory.CodeCType.JSON)
          .decode(rst, method.getReturnType());
      } finally {
      }
    });
  }
}
