package com.study.pv;

/**
 * @author wansong
 */
public class PvTest {
    public static void main(String[] args) {
        double fp=0.035;
        for (int i = 10;i<100;i=i+10){
            System.out.println(fp*100d+"\t"+i+"\t"+(Math.pow(1+fp,i)-1)*100/i);
        }
    }

}
