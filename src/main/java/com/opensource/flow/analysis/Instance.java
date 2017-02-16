package com.opensource.flow.analysis;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Aaron on 16/02/2017.
 */
public class Instance {
    private List<Object> activityInstances= Lists.newArrayList();
    public Instance add(Object activityId){
        activityInstances.add(activityId);
        return this;
    }

    public List<Object> getActivityInstances() {
        return activityInstances;
    }

    public void setActivityInstances(List<Object> activityInstances) {
        this.activityInstances = activityInstances;
    }
}
