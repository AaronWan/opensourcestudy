package com.orm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.bson.Document;
import org.mongodb.morphia.annotations.Embedded;

import java.util.HashMap;
import java.util.List;

@Data
@Embedded
public class TaskPojo extends Document{
        @Embedded
        public List<Object> actionParams;

        public static TaskPojo getInstance() {
            TaskPojo instance = new TaskPojo();
            HashMap<String,Object> data= Maps.newHashMap();
            data.put("fieldFlag","VARIABLE");
            data.put("toField","title"+System.currentTimeMillis());
            data.put("fromField","field_cr3WL__c"+System.currentTimeMillis());
            instance.setActionParams(Lists.newArrayList(data));
            instance.put("actionParams",instance.getActionParams());
            return instance;
        }
    }