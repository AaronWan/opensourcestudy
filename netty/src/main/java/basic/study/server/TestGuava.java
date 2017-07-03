package basic.study.server;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.MoreExecutors;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Aaron on 21/06/2017.
 */
public class TestGuava {

    @Test
    public void testV0() {
        BiMap<String, String> biMap = HashBiMap.create(10);
        biMap.put("1", "10");
        biMap.put("10", "10");
        System.out.println(biMap.toString());
    }


    @Test
    public void testArrayListEquals(){
        ArrayList arrayList= Lists.newArrayList("a","b");
        ArrayList arrayList1= Lists.newArrayList("b","a");
        System.out.println(CollectionUtils.isEqualCollection(arrayList,arrayList1));
        String a="";

    }
    @Test
    public void testMoreExecutors(){
        MoreExecutors.sameThreadExecutor();
    }

}
