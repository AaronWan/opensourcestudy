package com.opensource.rest.proxy.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by Aaron on 27/12/2016.
 */
public interface TriggerWorkflow {
    @Getter
    @Setter
    class Arg extends BaseArg{
        private String sourceWorkflowId;
        private Map<String,String> variables;
    }
    @Getter
    @Setter
    class Result extends BaseResult{

    }
}
