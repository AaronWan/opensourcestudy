package com.aita.collections;

import org.junit.Test;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class StackTest {
    @Test
    public void push() throws Exception {
        Stack stack = new Stack();
        for (int i = 0; i < 100000000; i++) {
            stack.push(new Object[10000]);
            Thread.sleep(200);
        }
        for (int i = 0; i < 100000000; i++) {
            stack.push(new Object[1000]);
            Thread.sleep(1000);
        }
        for (int i = 0; i < 100000000; i++) {
            stack.push(new Object[1000]);
            Thread.sleep(1000);
        }
        for (int i = 0; i < 100000000; i++) {
            stack.push(new Object[1000]);
            Thread.sleep(100);
        }
        for (int i = 0; i < 100000000; i++) {
            stack.push(new Object[1000]);
            Thread.sleep(1000);
        }

    }

    @Test
    public void iterator() throws Exception {
        Stack stack = new Stack();
        stack.push("test");
        stack.forEach(System.out::println);
    }

}