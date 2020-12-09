package com.aita.oop.classoop;

import java.util.Arrays;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ArrayTest {
    public static void main(String[] args) {
        int [] array={1,2,3,4,5};
        int[] ints = Arrays.copyOf(array, 10);
        System.out.println(Arrays.toString(ints));

    }
}
