package com.netty.study;

import com.netty.study.client.proxy.ServiceProxyFactory;
import com.netty.study.server.Server;
import com.netty.study.service.FirstRpcService;
import com.netty.study.server.model.ServiceResponse;
import com.netty.study.service.model.*;
/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/9
 * @creat_time: 22:19
 * @since 7.3.5
 */
public class NettyTestClient {
  public static void main(String[] args) throws Exception {
    new Thread(() -> {
      try {
        Server.start();
      } catch (InterruptedException e) {
      }
    }).start();

    ServiceProxyFactory client = new ServiceProxyFactory("localhost", 8080);
    FirstRpcService firstRpcService = client.newRestServiceProxy(FirstRpcService.class);
    TestMethodBean.Arg arg=new TestMethodBean.Arg();
    arg.setData("test");
    ServiceResponse<TestMethodBean.Result> result;
    result = firstRpcService.test2(arg);
    System.out.println(result.getBody().getData());
  }
}
