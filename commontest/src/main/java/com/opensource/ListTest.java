package com.opensource;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by Aaron on 17/02/2017.
 */
public class ListTest {
    private List<Integer> list=Lists.newArrayList(21,34,5,2,4);
    @Test
    public void test(){
        list.sort((o1,o2)->{return o1-o2;});
        System.out.println(list);
    }
}
