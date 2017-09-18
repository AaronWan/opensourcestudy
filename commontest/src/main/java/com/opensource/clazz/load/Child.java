package com.opensource.clazz.load;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Child extends Parent {
    static {
        System.out.println("Child static");
    }

    {
        System.out.println("Child {}");
    }

    public Child() {
        System.out.println("Child con");
    }


    public static void main(String[] args) {
        new Child();
        System.out.println("------");
        new Child();
    }
}
