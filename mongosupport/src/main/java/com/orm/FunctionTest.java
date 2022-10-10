package com.orm;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.query.Query;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class FunctionTest {
    /**
     * db.system.js.insert({
     * <p>
     * 　　"_id": "getNextValue",
     * <p>
     * 　　value:function(colName) {
     * <p>
     * 　　　　　　var res = db.counters.findAndModify({
     * <p>
     * 　　　　　　　　query: {"_id": colName},
     * <p>
     * 　　　　　　　　update: {$inc: {current_value: 1}},
     * <p>
     * 　　　　　　});
     * <p>
     * 　　　　return res.current_value;
     * <p>
     * 　　}
     * <p>
     * })
     *
     * @param args
     */
    public static void main(String[] args) {
        try (MongoClient client = new MongoClient("localhost")) {

            com.mongodb.DB db = client.getDB("FS-PAAS-WORKFLOW-DB");
            // Command failed with error 59: 'no such command: '$eval'' on server localhost:27017. The full response is { "ok" : 0.0, "errmsg" : "no such command: '$eval'", "code" : 59, "codeName" : "CommandNotFound" }
            db.doEval("getNextValue").toString();
        }
    }
}
