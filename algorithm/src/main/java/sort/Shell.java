package sort;

import common.RandomDataUtil;

import java.util.Arrays;

/**
 * @author 万松(Aaron)
 *         希尔排序
 *         todo 需要理解一下
 * @since 5.7
 */
public class Shell<T> implements ISort<T> {
    @Override
    public void sort(Comparable<T>[] objects) {
        int n = objects.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(objects[j], objects[j - h]) < 0; j -= h) {
                    exch(objects, j, j - h);
//                    System.out.println(Arrays.toString(objects));
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        new Shell<Integer>().sort(RandomDataUtil.createIntArrays(10));
    }
}
