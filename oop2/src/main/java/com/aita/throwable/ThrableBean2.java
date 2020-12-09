package com.aita.throwable;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ThrableBean2 {
    public String say()  {
        String c;
        try {
//            new ThrableBean2().say();
            throw new FileNotFoundException();
//            System.out.println("test 1");
        } catch (FileNotFoundException e) {
            System.out.println(e);
            c= "a";
        } catch (IOException e) {
            System.out.println(e);
            c= "b";
        }finally {
            //destroy some resource
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new ThrableBean2().say());

    }
}
