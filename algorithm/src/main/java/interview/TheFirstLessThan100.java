package interview;

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
                System.out.printf("times:%d,index:%d,value:%d\n", count, middle, value);
                return middle;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 7, 7, 7, 8, 9, 10, 14, 17, 19, 20};
        for (int i = 0; i < array.length; i++) {
            int index = find(array, array[i]);
        }
    }
}
