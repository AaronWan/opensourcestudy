package com.aita.other;

public class BubbleSort {
    public void bubbleSort() {
        int arr[] = {-5, 29, 7, 10, 5, 16};
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = 0;
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i] + " ");
        }

    }


    public static void main(String[] args) {
        BubbleSort myBubbleSort = new BubbleSort();
        myBubbleSort.bubbleSort();
    }

}
