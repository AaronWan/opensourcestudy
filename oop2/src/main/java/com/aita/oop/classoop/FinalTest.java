package com.aita.oop.classoop;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class FinalTest {

    public static int sum(int... value) {
        int sum=0;
        for (int i = 10; i < value.length; i++) {
            sum+=value[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sum(new int[]{1,2,3}));
    }
}

class Demo {
    private final int x;

    public Demo(int x) {
        this.x = x;
    }


}