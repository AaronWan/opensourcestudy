package search;

import java.io.IOException;

public class BinaryTest {
  public static int binary(int[] array, int value) {
    int low = 0, high = array.length - 1;
    while (low <= high) {
      int middle = (low + high) / 2;
      if (value == array[middle]) {
        return middle;
      } else if (value > array[middle]) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(binary(new int[] {1, 2, 3, 4}, 3));
  }


}
