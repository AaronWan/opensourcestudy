package com.netty.study.service;

import com.netty.study.server.annotation.ServerService;
import com.netty.study.server.annotation.ServerServiceMethod;
import com.netty.study.server.model.ServiceResponse;
import com.netty.study.service.model.*;
/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/14
 * @creat_time: 23:52
 * @since 7.3.5
 */
@ServerService("a")
public interface FirstRpcService {
  @ServerServiceMethod("b")
  ServiceResponse<TestMethodBean.Result> test(TestMethodBean.Arg arg);


  @ServerServiceMethod("c")
  ServiceResponse<TestMethodBean.Result> test2(TestMethodBean.Arg arg);
}
