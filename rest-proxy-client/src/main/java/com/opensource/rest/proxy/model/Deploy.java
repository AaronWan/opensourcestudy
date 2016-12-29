package com.opensource.rest.proxy.model;

import lombok.Getter;
import lombok.Setter;

public interface Deploy {
    /**
     * Created by Aaron on 26/12/2016.
     */
    @Setter
    @Getter
    class Arg extends BaseArg{
        private String workflowJson;
    }
    @Setter
    @Getter
    class Result extends BaseResult{
        private String result;
    }
}
