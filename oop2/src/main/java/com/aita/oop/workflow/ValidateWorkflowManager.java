package com.aita.oop.workflow;

import com.aita.oop.workflow.handler.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aita on 17/8/27.
 */
public class ValidateWorkflowManager implements ValidateHandler{

    List<ValidateHandler> handlers;

    public ValidateWorkflowManager(List<ValidateHandler> handlers) {
        this.handlers = handlers;
    }

    /**
     * 节点
     * 线
     * 条件
     * @param workflow
     * @return
     */
    public boolean validate(Workflow workflow){
        Boolean rst=new Boolean(true);
        for (int i = 0; i < handlers.size(); i++) {
            rst=rst&&handlers.get(i).validate(workflow);
        }
        return rst;
    }


    public static void main(String[] args) {
        List<ValidateHandler> handlers=new ArrayList<>();
        handlers.add(new ActivityValidateHandler());
        handlers.add(new ConditionValidateHandler());
        handlers.add(new TransitionValidateHandler());

        ValidateWorkflowManager validateManager = new ValidateWorkflowManager(handlers);

        Workflow workflow=new Workflow();
        boolean rst=validateManager.validate(workflow);
        System.out.println(rst);
    }
}
