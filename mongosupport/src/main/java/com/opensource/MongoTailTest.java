package com.opensource;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


/**
 * Hello world!
 *todo tail mongo
 */
public class MongoTailTest
{

    public static void main(String[] args )
    {
        MongoCursor<Document> cursor=new MongoTailTest().getCursor();
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }

    public MongoCursor<Document> getCursor(){
        MongoDatabase db = getClient().getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("TestCursor");
        MongoCursor<Document> cursor =collection.find().noCursorTimeout(true)
//                .oplogReplay(true)
//                .cursorType(CursorType.Tailable)
//                .cursorType(CursorType.TailableAwait)
                .iterator();

        return cursor;
    }

    private MongoClient getClient() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.socketKeepAlive(true)
                .maxWaitTime( 120000)
                .connectionsPerHost(100)
                .connectTimeout( 5000)
                .socketTimeout( 60000);

        MongoClientURI clientURI = new MongoClientURI("mongodb://localhost:27017/test", builder);
        MongoClient client = new MongoClient(clientURI);
        return client;
    }

}
