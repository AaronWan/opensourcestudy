package com.algorithm.collections;

import collections.Stack;
import org.junit.Test;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class StackTest {
    @Test
    public void testStack(){
        Stack<Integer> stack=new Stack<>();
        for (int i=0;i<1000;i++)
        stack.push(i);

        stack.forEach(System.out::println);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
