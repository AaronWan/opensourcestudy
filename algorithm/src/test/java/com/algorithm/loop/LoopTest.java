package com.algorithm.loop;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class LoopTest {
    @Test
    public void test1() {
        List<String> a = Lists.newArrayList();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a);
        }
    }

    @Test
    public void test2() {
        List<String> a = Lists.newArrayList();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        printLoop(a, 0);
    }

    private void printLoop(List<String> a, int i) {
        if (i < a.size() - 1) {
            System.out.println(a.get(i));
            printLoop(a, i + 1);
        } else if (i == a.size() - 1) {
            System.out.println(a.get(i));
        }
    }
}
