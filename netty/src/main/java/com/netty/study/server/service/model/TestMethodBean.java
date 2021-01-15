package com.netty.study.server.service.model;

import lombok.Data;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/14
 * @creat_time: 23:54
 * @since 7.3.5
 */
public interface TestMethodBean {
  @Data
  class Arg{
    String data;
  }
  @Data
  class Result{
    String data;
  }
}
