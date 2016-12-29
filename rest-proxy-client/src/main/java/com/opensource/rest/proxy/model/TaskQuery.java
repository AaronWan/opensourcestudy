package com.opensource.rest.proxy.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Aaron on 29/12/2016.
 */
@Getter
@Setter
public class TaskQuery {
    private String sourceWorkflowId;
    private String workflowId;
    private String assignee;
    private Boolean isCompleted;
}
