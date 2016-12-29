package com.opensource.rest.proxy;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Aaron on 26/12/2016.
 */
@Data
public class RestServiceProxyFactoryBean<T> implements FactoryBean {
    private RestServiceProxyFactory factory;
    private Class<T> type;

    @Override
    public T getObject() throws Exception {
        return factory.newRestServiceProxy(type);
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
