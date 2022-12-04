package com.algorithm.collections;

import collections.Stack;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class StackTest {
    @Test
    public void testStack() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 1000; i++)
            stack.push(i);

        stack.forEach(System.out::println);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    @Test
    public void testA() {
        Map<List<String>, List<String>> map = Maps.newHashMap();
        map.put(Lists.newArrayList("a", "b"), Lists.newArrayList("a", "b"));
        System.out.println(new GsonBuilder().create().toJson(map));
    }
}
