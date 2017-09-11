package sort;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author 万松(Aaron)
 * 选择排序
 * @since 5.7
 */
public class Selection<T> implements ISort<T> {

    @Override
    public void sort(Comparable<T>[] objects) {
        for (int i = 0; i < objects.length; i++) {
            int min=i;
            for (int j = i; j <objects.length ; j++) {
                if(less(objects[min],objects[j])>0){
                    min=j;
                }
            }
            if(min!=i){
                exch(objects,min,i);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{12,32,4,3,4,5,465,655,534,3,2,3,2,43,5,4,56};
        System.out.println(Objects.toString(arr));
        new Selection().sort(arr);
    }
}
