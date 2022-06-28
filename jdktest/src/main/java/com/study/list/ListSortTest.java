package com.study.list;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class ListSortTest {
    public static void main(String[] args) {
        sort();
    }

    private static void sort() {
        List<Integer> list= Lists.newArrayList(3,2,0,1,0,10);
        list.sort((o1, o2) -> o1-o2);//desc
        System.out.println(list);
        list.sort((o1, o2) -> -(o1-o2));//asc
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
