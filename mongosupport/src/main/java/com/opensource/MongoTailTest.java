package com.opensource;

import com.github.fakemongo.Fongo;
import com.mongodb.*;

/**
 * Hello world!
 *todo tail mongo
 */
public class MongoTailTest
{
  public static void main(String[] args) {
    Fongo fongo = new Fongo("mongo server 1");

    // once you have a DB instance, you can interact with it
    // just like you would with a real one.
    DB db = fongo.getDB("mydb");
    DBCollection collection = db.getCollection("mycollection");
    collection.insert(new BasicDBObject("name", "jon"));
  }

}
