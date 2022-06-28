package com.netty.study.server.register;

import com.google.common.collect.Maps;
import com.netty.study.server.annotation.ServerService;
import com.netty.study.server.annotation.ServerServiceMethod;
import com.netty.study.server.model.ServiceModel;
import com.netty.study.service.FirstRpcService;
import com.netty.study.service.impl.FirstRpcServiceImpl;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:39
 * @since 7.3.5
 */
public class ServerServiceRegister {
  static Map<String, ServiceModel> serviceModelMap = Maps.newConcurrentMap();

  /**
   * service register
   */static {
//    try {
//      Class<FirstRpcService> serviceClazz = FirstRpcService.class;
//      ServerService serverService = serviceClazz.getDeclaredAnnotation(ServerService.class);
//      if (serverService != null) {
//        FirstRpcService instance = serviceClazz.getGenericSuperclass().getClass().;
//        for (Method method : serviceClazz.getMethods()) {
//          ServiceModel a = new ServiceModel();
//          ServerServiceMethod methodDesc = method.getDeclaredAnnotation(ServerServiceMethod.class);
//          if (methodDesc != null) {
//            a.setMethod(method);
//            a.setInstance(instance);
//            a.setArgClazz(method.getParameterTypes()[0]);
//            serviceModelMap.put(serverService.value() + "." + methodDesc.value(), a);
//          }
//        }
//      }
//    } catch (IllegalAccessException e) {
//      e.printStackTrace();
//    } catch (InstantiationException e) {
//      e.printStackTrace();
//    }
  }

  public static ServiceModel getServiceModel(String request) {
    return serviceModelMap.get(request);
  }
}
