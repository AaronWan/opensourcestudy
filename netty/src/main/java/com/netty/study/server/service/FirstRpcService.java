package com.netty.study.server.service;

import com.netty.study.server.annotation.ServerService;
import com.netty.study.server.annotation.ServerServiceMethod;
import com.netty.study.server.model.ServiceResponse;
import com.netty.study.server.service.model.TestMethodBean;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/14
 * @creat_time: 23:52
 * @since 7.3.5
 */
@ServerService("a")
public class FirstRpcService {
  @ServerServiceMethod("b")
  public ServiceResponse<TestMethodBean.Result> test(TestMethodBean.Arg arg) {
    ServiceResponse<TestMethodBean.Result> response = new ServiceResponse<>();
    TestMethodBean.Result result = new TestMethodBean.Result();
    result.setData(arg.getData() + " ~~ get it!");
    response.setBody(result);
    response.setCode("200");
    return response;
  }
}
