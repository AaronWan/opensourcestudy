package com.aita.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aita on 17/8/27.
 */
public class ListTest {

    @Test
    public void testArrayListNoType(){
        List lists=new ArrayList();
        lists.add(1);
        lists.add("String");
        lists.add(true);
        System.out.println(lists);

        Object obj=lists.get(2);
        boolean objs= (boolean) obj;
        Assert.assertTrue(objs);
    }


    @Test
    public void testArrayList(){
        List<String> lists=new ArrayList<>();
        lists.add("String");
        System.out.println(lists);

        String obj=lists.get(0);
        System.out.println(obj);
    }

    @Test
    public void iterator(){
        List<Person> persons= new ArrayList<>(100);
        persons.add(new Person("name1"));
        persons.add(new Person("name2"));
        persons.add(new Person("name3"));
        persons.add(new Person("name4"));

        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i).getName());
        }


        for(Person person:persons){
            System.out.println(person.getName());
        }

        Iterator<Person> it = persons.iterator();
        while (it.hasNext()){
            System.out.println(it.next().getName());
        }

//        lambda
        persons.forEach(person -> System.out.println(person.getName()));
        persons.stream().forEach(person -> {
            System.out.println(person.getName());
        });


        List<String> names=persons.stream().map(person -> person.getName()).collect(Collectors.toList());
        System.out.println(names);

    }



}
