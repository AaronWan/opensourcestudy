package sort;

import common.RandomDataUtil;
import org.apache.commons.lang.math.RandomUtils;

import java.util.Arrays;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Insertion<T> implements ISort<T> {
    @Override
    public void sort(Comparable<T>[] objects) {
        for (int i = 1; i < objects.length; i++) {
            int min=i;
            for (int j = i-1; j >=0; j--) {
                if(less(objects[min],objects[j])<0){
                    exch(objects,min,j);
                    min=j;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr= RandomDataUtil.createIntArrays(10);
        new Insertion().sort(arr);
    }
}
