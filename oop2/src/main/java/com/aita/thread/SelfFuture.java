package com.aita.thread;

import java.io.FileNotFoundException;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class SelfFuture{
    public static void main(String[] args) throws FileNotFoundException {
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j < 8; j++) {
                int total=i+j;
                if(total%2==0){
                    System.out.print("\033[47m  ");
                }else {
                    System.out.print("\033[40m  ");
                }
            }
            System.out.println();
        }
    }

}
