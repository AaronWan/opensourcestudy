package com.aita.oop.classoop;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Person extends Animal {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
//        System.out.println("Person");
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    protected Person clone() throws CloneNotSupportedException {

        return new Person(this.name, this.age);

    }

    @Override
    public synchronized void say() {

        System.out.println("hi zheng,i am " + name);
    }

    @Override
    public int hashCode() {
        return this.age;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person))
            return false;

        return this.name.equals(((Person) obj).name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
//        System.out.println("........");
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
