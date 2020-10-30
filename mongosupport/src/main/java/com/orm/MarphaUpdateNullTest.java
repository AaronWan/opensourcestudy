package com.orm;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.UUID;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class MarphaUpdateNullTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(Dept.class);
        Datastore datastore = morphia.createDatastore(client, "test_null");

        Dept dept=new Dept();

        Key<Dept> rst = datastore.save(dept);
        Object id = rst.getId();

        Query<Dept> result = datastore.find(Dept.class);
        result.forEach((item)->{
            System.out.println(item);
        });
        Query<Dept> query=datastore.createQuery(Dept.class);
        query.field("_id").equal(id);
        UpdateOperations<Dept> update=datastore.createUpdateOperations(Dept.class);
        update.set("leader", null);
        datastore.findAndModify(query,update);

        update=datastore.createUpdateOperations(Dept.class);
        update.set("leader", "test");
        datastore.findAndModify(query,update);

        update=datastore.createUpdateOperations(Dept.class);
        update.set("leader", null);
        datastore.findAndModify(query,update);
    }



}
