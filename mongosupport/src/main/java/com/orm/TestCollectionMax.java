package com.orm;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class TestCollectionMax {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("TestCollectionMax");
        for(int i=0;;i++){
            Document document=new Document();
            document.put("name", "name"+i);
            database.getCollection("Test_"+i).insertOne(document);
            if(i%1000==0){
                log.info("created:{}",i);
            }
        }

    }



}
