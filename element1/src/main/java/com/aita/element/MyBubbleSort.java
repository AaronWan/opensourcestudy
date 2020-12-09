package com.aita.element;

/**
 * Created by Aita on 17/8/13.
 */
public class MyBubbleSort {
    public static void main(String[] args) {
        MyBubbleSort s= new MyBubbleSort();
        s.bubbleSort();
    }

    public void bubbleSort(){
        int arr[]={2,35,8,7,4,9};
        for(int i=1;i<arr.length;i++) {
            for (int j = 0; j < arr.length - i; j++) {
                int temp=0;
                if(arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }

    }

}
