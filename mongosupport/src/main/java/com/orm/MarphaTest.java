package com.orm;

import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import lombok.Data;
import org.bson.types.ObjectId;
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
public class MarphaTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(Dept.class);
        Datastore datastore = morphia.createDatastore(client, "test_student");
        Dept dept=new Dept();

        List members=new ArrayList();
        members.add("test");
        dept.setLeader(members);
        datastore.save(dept);

        Query<Dept> result = datastore.find(Dept.class);
        result.forEach((item)->{
            System.out.println(item);
        });
    }
    @Data
    @Entity(value = "Dept",noClassnameStored = true)
    static class Dept implements Serializable{
        @Id
        private String id;
        @Embedded
        private Object leader;
    }
    @Data
    static class Member implements Serializable{
        @Property("name")
        private String name;
        @Property("workAge")
        private String workAge;
    }
}