package com.opensource.rest.proxy;

import com.google.common.base.Strings;
import com.google.common.reflect.Reflection;
import com.opensource.rest.proxy.annotation.RestResource;
import com.opensource.rest.proxy.annotation.RestUri;
import com.opensource.rest.proxy.exception.RestInvokeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by Aaron on 26/12/2016.
 */
@Slf4j
@Data
public class RestServiceProxyFactory {
    private String configName;
    private Map<String, RestServiceConfig> serviceConfigMaps = new HashMap<>();
    private final static RestClient restClient = new RestClient();
    public RestServiceProxyFactory(){
    }
    public void init() {
        /**
         * @TODO 设置配置文件
         */
    }


    public <T> T newRestServiceProxy(Class<T> clazz) {
        return Reflection.newProxy(clazz, (Object proxy, Method method, Object[] args) -> {
            RestResource restResource = method.getDeclaringClass().getAnnotation(RestResource.class);
            String serviceName = restResource.value();
            RestUri restUri = method.getDeclaredAnnotation(RestUri.class);
            String uri = restUri.value();
            String contentType = restUri.contentType();
            log.debug("newRestServiceProxy invoke {}:{}", restResource.value(), uri);
            String serviceUrl = getResourceAddress(serviceName, uri);

            Map<String, String> headers = new LinkedHashMap<>(4);
            headers.put("Content-Type", contentType);


            Object ret = null;
            try {
                switch (restUri.method()) {
                    case POST:
                        ret = restClient.post(serviceName, serviceUrl, headers, args[0], method.getReturnType());
                        break;
                    case GET:
                        ret = restClient.get(serviceName, serviceUrl + "?" + args[0], headers, method.getReturnType());
                        break;
                }
            } finally {
                log.debug("\n\nurl:{},\narg:{},\nresult:{}\n\n",serviceUrl,args[0],ret);
            }
            if (BaseResult.class.isInstance(ret)) {
                BaseResult result = (BaseResult) ret;
            }
            return ret;
        });
    }

    private String getResourceAddress(String serviceName, String restUri) {
        if (Strings.isNullOrEmpty(serviceName)) {
            throw new RestInvokeException("ServiceName is null,please check config:" + configName + "->" + serviceName);
        }
        RestServiceConfig config = serviceConfigMaps.get(serviceName);
        if (config == null) {
            throw new RestInvokeException("ServiceName is null,please check config:" + configName + "->" + serviceName);
        }
        String template = "http://%s";
        if (restUri.startsWith("/")) {
            template += "%s";
        } else {
            template += "/%s";
        }
        return String.format(template, config.address, restUri);
    }

    @Data
    @AllArgsConstructor
    public static class RestServiceConfig {

        private String address;

        private int socketTimeOut;

        private int connectionTimeOut;
    }
}
