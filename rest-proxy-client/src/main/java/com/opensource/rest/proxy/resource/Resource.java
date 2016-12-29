package com.opensource.rest.proxy.resource;


import com.opensource.rest.proxy.annotation.MethodType;
import com.opensource.rest.proxy.annotation.RestResource;
import com.opensource.rest.proxy.annotation.RestUri;
import com.opensource.rest.proxy.model.Deploy;

/**
 * Created by Aaron on 26/12/2016.
 */
@RestResource(value="Rest",desc="Rest接口调用")
public interface Resource {

    @RestUri(value = "/paas/af/deploy",method = MethodType.POST)
    Deploy.Result deploy(Deploy.Arg arg);
}
