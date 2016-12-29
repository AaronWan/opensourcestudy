package com.opensource.rest.proxy.model;

import com.opensource.rest.proxy.common.JsonUtil;
import lombok.Data;

/**
 * Created by Aaron on 27/12/2016.
 */
@Data
public class BaseArg {
    protected String tenantId;
    protected String appId;
    protected String userId;
    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
