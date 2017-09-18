package com.opensource.clazz.load;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Parent {
    static {
        System.out.println("Parent static");
    }

    {
        System.out.println("Parent {}");
    }

    public Parent() {
        System.out.println("Parent Con");
    }
}
