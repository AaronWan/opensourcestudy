package com.aita.oop.workflow.handler;

/**
 * Created by Aita on 17/8/27.
 */
public class ConditionValidateHandler implements ValidateHandler {


    @Override
    public boolean validate(Workflow workflow) {
        System.out.println("validate  Condition Handler");
        return true;
    }
}
