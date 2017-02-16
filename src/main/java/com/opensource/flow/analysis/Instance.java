package com.opensource.flow.analysis;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by Aaron on 16/02/2017.
 */
@Data
public class Instance {
    private List<Object> activityInstances= Lists.newArrayList();
    public Instance add(Object activityId){
        activityInstances.add(activityId);
        return this;
    }
}
