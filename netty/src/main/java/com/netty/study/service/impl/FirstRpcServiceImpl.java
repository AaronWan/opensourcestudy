package com.netty.study.service.impl;

import com.netty.study.server.model.ServiceResponse;
import com.netty.study.service.FirstRpcService;
import com.netty.study.service.model.*;
/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/14
 * @creat_time: 23:52
 * @since 7.3.5
 */
public class FirstRpcServiceImpl implements FirstRpcService {
  @Override
  public ServiceResponse<TestMethodBean.Result> test(TestMethodBean.Arg arg) {
    ServiceResponse<TestMethodBean.Result> response = new ServiceResponse<>();
    TestMethodBean.Result result = new TestMethodBean.Result();
    result.setData(arg.getData() + " ~~ get it!");
    response.setBody(result);
    response.setCode("200");
    return response;
  }

  @Override
  public ServiceResponse<TestMethodBean.Result> test2(TestMethodBean.Arg arg) {
    ServiceResponse<TestMethodBean.Result> response = new ServiceResponse<>();
    TestMethodBean.Result result = new TestMethodBean.Result();
    result.setData(arg.getData() + " 锄禾日当午，汗滴禾下土!");
    response.setBody(result);
    response.setCode("200");
    return response;
  }


}
