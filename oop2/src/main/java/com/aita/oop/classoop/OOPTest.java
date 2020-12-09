package com.aita.oop.classoop;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class OOPTest {

    public static void main(String[] args) throws InterruptedException {
        Animal animal=new Person("wansong",30);
        animal.say();
        Person p= (Person) animal;
        System.out.println(p.getAge());

        System.out.println(animal.getClass().getName());
        for (int i = 0; i < 100000; i++) {
            animal=new Dog();
        }

        animal.say();
        Runtime.getRuntime().gc();
        Thread.sleep(10000);

    }
}
