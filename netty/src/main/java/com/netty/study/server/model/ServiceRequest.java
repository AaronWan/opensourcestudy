package com.netty.study.server.model;

import lombok.Data;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:17
 * @since 7.3.5
 */
@Data
public class ServiceRequest {
  private String serviceMethod;
  private byte[] body;
}
