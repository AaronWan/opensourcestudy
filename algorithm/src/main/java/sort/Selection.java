package sort;

/**
 * @author 万松(Aaron)
 * 选择排序
 * @since 5.7
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 重复第二步，直到所有元素均排序完毕。
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
        System.out.println(arr);
        new Selection().sort(arr);
    }
}
