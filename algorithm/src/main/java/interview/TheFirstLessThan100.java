package interview;

import java.util.Arrays;

/**
 * @author Aaron
 * @since 6.2
 */
public class TheFirstLessThan100 {
    public static int find(int[] array, int value) {

        int low = 0;
        int high = array.length - 1;
        int count = 0;
        while (low <= high) {
            int middle = (low + high) >>> 1;
            int middleValue = array[middle];
            count++;
            if (middleValue < value) {
                low = middle + 1;
            } else if (middleValue > value) {
                high = middle - 1;
            } else {
//                System.out.printf("times:%d,index:%d,value:%d,middle:%d\n", count, middle, value,middleValue);
                return middle;
            }
        }
        return -1;
    }


    public static int findFirstBigIndex(int[] array, int value) {

        int lastBigIndex=-1;

        int low = 0;
        int high = array.length - 1;
        int count = 0;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            int middleValue = array[middle];
            count++;
            if (middleValue <= value) {
                low = middle + 1;
            } else if (middleValue > value) {
                high = middle - 1;
                lastBigIndex=middle;
                System.out.printf("times:%d,index:%d,value:%d,middle:%d\n", count, middle, value,middleValue);
            }
        }
        return lastBigIndex;
    }



    public static int findFirstBigIndex1(int[] array, int value) {

        int low = 0;
        int high = array.length - 1;
        int count = 0;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            int middleValue = array[middle];
            count++;
            if (middleValue <= value) {
                low = middle + 1;
            } else if (middleValue > value) {
                high = middle - 1;
                System.out.printf("times:%d,index:%d,value:%d,middle:%d\n", count, middle, value,middleValue);
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 8, 9, 10, 14, 17, 19, 20,21,22,34,324,546};
        for (int i = 0; i < array.length; i++) {
            int index = findFirstBigIndex1(array, i);
            int index2 = findFirstBigIndex(array, i);
            System.out.println(index+"--"+index2);
        }
    }
}
