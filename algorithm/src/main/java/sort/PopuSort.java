package sort;

import java.util.Arrays;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/10/14
 * @creat_time: 13:21
 * @since 7.3.5
 */
public class PopuSort implements ISort<Integer>{
  @Override
  public void sort(Comparable<Integer>[] objects) {
    int l=objects.length;
    for (int i = 0; i < l; i++) {
      Integer current= (Integer) objects[i];
      for (int j = i; j < l; j++) {
        Integer jNumber= (Integer) objects[j];
        if(jNumber<current){
          exch(objects,i,j);
        }
      }
    }
  }

  public static void main(String[] args) {
    Integer[] data=new Integer[]{3,2,1,4};
    new PopuSort().sort(data);
    System.out.println(Arrays.toString(data));
  }
}
