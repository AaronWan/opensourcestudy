package com.aita.oop.workflow.handler;

import java.util.List;

/**
 * Created by Aita on 17/8/27.
 */
public class Workflow {
    private List<Activity> activities;
    private List<Transition> transitions;
    private List<Condition> conditions;

    public class Activity{

    }
    public class Transition{

    }
    public class Condition{

    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
