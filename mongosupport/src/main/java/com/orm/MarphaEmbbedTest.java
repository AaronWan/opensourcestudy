package com.orm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.MongoClient;
import lombok.Data;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class MarphaEmbbedTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        Morphia morphia = new Morphia();
//        morphia.map(Dept.class);
        Datastore datastore = morphia.createDatastore(client, "test_student");

        Dept dept=new Dept();

        List members=new ArrayList();
        members.add("test");
        dept.setLeader(members);

        Map<String, List<TaskPojo>> execution= Maps.newHashMap();
        execution.put("pass", Lists.newArrayList(TaskPojo.getInstance()));
        execution.put("reject", Lists.newArrayList());
        dept.setExecution(execution);
        datastore.save(dept);

        Query<Dept> result = datastore.find(Dept.class);
        result.forEach((item)->{
            System.out.println(item);
        });
    }



}
