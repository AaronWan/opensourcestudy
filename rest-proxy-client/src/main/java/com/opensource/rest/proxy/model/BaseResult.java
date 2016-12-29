package com.opensource.rest.proxy.model;

import com.opensource.rest.proxy.common.JsonUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Aaron on 26/12/2016.
 */
@Getter
@Setter
public class BaseResult {
    protected  boolean success;
    protected String errMessage;//错误信息（由业务端生成）
    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
