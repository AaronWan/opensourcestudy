package com.orm;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import lombok.Data;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class MarphaTest2ListAddAndSet {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(ListAddBean.class);
        Datastore datastore = morphia.createDatastore(client, "test_student");
//        datastore.save(createBean());
        Query<ListAddBean> result = datastore.find(ListAddBean.class);
        result.forEach((item)->{
            System.out.println(item);
        });
    }

    @Test
    public  void findUpdateBean(){
        MongoClient client = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(ListAddBean.class);
        Datastore datastore = morphia.createDatastore(client, "test_student");
        Query<ListAddBean> query = datastore.createQuery(ListAddBean.class);
        String userId="5";
        query.field("opinions.userId").notEqual(userId);
        System.err.println(query.toString());
        UpdateOperations<ListAddBean> updates=datastore.createUpdateOperations(ListAddBean.class);
        updates.add("opinions",new ListAddBean.Opinion(userId,"user opinion"+userId));
        ListAddBean list = datastore.findAndModify(query, updates,false,false);
        System.err.println(list);

    }

    static ListAddBean createBean(Datastore datastore ){
        ListAddBean data=new ListAddBean();
        List<ListAddBean.Opinion> opinions=Lists.newArrayList();
        opinions.add(new ListAddBean.Opinion("1","kkk"));
        opinions.add(new ListAddBean.Opinion("2","2kk"));
        data.setOpinions(opinions);
        Query<ListAddBean> result = datastore.find(ListAddBean.class);
        result.forEach((item)->{
            System.out.println(item);
        });
        return data;
    }
    @Data
    @Entity(value = "ListAddCollection",noClassnameStored = true)
    static class ListAddBean implements Serializable{
        @Id
        private String id;
        @Embedded
        private List<Opinion> opinions;
        @Data
        private static class Opinion {
            private String userId;
            private String opinion;

            public Opinion() {
            }

            public Opinion(String userId, String opinion) {
                this.userId = userId;
                this.opinion = opinion;
            }
        }
    }


}
