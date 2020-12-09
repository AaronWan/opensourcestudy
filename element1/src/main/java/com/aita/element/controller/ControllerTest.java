package com.aita.element.controller;

import org.junit.Assert;
import org.junit.Test;

import static com.aita.element.controller.ControllerTest.Season.*;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */

public class ControllerTest {

    @Test
    public void controller() {

        for (int i = 0; i < 10; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();

        Season i = Season.FALL;
        switch (i) {
            case SPRING:
                System.out.println(SPRING);
            case SUMMER:
                System.out.println(SUMMER);
            case FALL:
                System.out.println(FALL);
            default:
                System.out.println(WINTER);
        }

        int j = 10;
        do {
            System.out.print(j-- + "\t");
        } while (j >= 0);
        System.out.println();


        for (int k = 0, l = 0; k < 10 && l < 10; k++, l++) {
            System.out.print(k + "\t" + l + ",");
        }
        System.out.println();

        for (; ; ) {
            System.out.println("hehe");
            break;
        }


        for (int k = 0; k < 10; k++) {
            if (k == 2) {
                continue;
            }

            System.out.print(k + "\t");
        }
        System.out.println("\n\n");

        bk:
        for (int k = 1; k <= 9; k++) {

            for (int l = 1; l <= 9; l++) {
                if (k == 7) {
                    break bk;
                }
                System.out.printf("%d*%d=%d\t", k, l, k * l);
            }

            System.out.println();
        }

    }

    enum Season {
        SPRING, SUMMER, FALL, WINTER;
    }

    @Test
    public void plus() {
        int i = 100;
        int j = 20;
        Assert.assertEquals(i - j, 80);
        Assert.assertEquals(i + j, 120);
        Assert.assertEquals(i / j, 5);
        Assert.assertEquals(i % j, 0);
        Assert.assertEquals(100, i++);
        Assert.assertEquals(102, ++i);
        Assert.assertEquals(4, i & j);
        Assert.assertEquals(118, i | j);
        Assert.assertEquals(114, i ^ j);
        Assert.assertEquals(2, 1 << 1);
        Assert.assertEquals(1, 2 >> 1);

        Assert.assertEquals(0x7fffffff, -0x00000002 >>> 1);

        i -= 1;//i=i-1;i--
        Assert.assertEquals(101, i);
        i += 1;
        Assert.assertEquals(102, i);

        i = i == 102 ? 101 : 102;
        Assert.assertEquals(101, i);

        i = (int) Character.MAX_VALUE;

        System.out.println(i);


    }

    @Test
    public void comment() {
         /*
        lksdjf
        sdfljd
        ldsjflds

         */
        //lksdjflkjdfl
        //jslkdjflkdsjf

        String s = "100";
        String s2 = s;
        System.out.println(s == s2);
        s = null;
        System.out.println(s2);
    }
}
