package com.junit.runner.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019/1/1
 * @creat_time: 10:32
 * @since 6.4
 */
@RunWith(value = Parameterized.class)
public class TestParameters {

    private String a;
    private int b;
    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[]{
                new Object[]{"1", 2},
                new Object[]{"2", 3},
                new Object[]{"3", 4},
                new Object[]{"4", 5},
                new Object[]{"5", 6},
                new Object[]{"6", 7}
        };
    }

    public TestParameters(String a, int b) {
        this.a = a;
        this.b = b;
    }

    @Test
    public void testAMethod() {
        System.out.println("A: a:" + a + "b:" + b);
    }

    @Test
    public void testBMethod() {
        System.out.println("B: a:" + a + "b:" + b);
    }
}
