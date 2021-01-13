package com.netty.study.server.register;

import com.netty.study.server.model.ServiceModel;

import java.util.Map;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:39
 * @since 7.3.5
 */
public class ServerServiceRegister {
  Map<String, ServiceModel> serviceModelMap;

  /**
   * service register
   */
  public void init(String servicePackage) {

  }

  public ServiceModel getServiceModel(String request) {
    return serviceModelMap.get(request);
  }
}
