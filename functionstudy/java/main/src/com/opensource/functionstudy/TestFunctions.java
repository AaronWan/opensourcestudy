package com.opensource.functionstudy;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.lang.*;
import java.util.List;

/**
 * Created by Aaron on 08/03/2017.
 */
public class TestFunctions {
    @Test
    public void testFunction(){
        List<Person> persons= Lists.newArrayList();
        TestFunction<Person> testFunction = person -> persons.add(person);

        addPerson(testFunction);
    }

    @Test
    public void testOtherFunction(){
        addPerson(Object::notifyAll);
    }


    public  void addPerson(TestFunction<Person> testFunction) {
        testFunction.callTest(new Person());
    }

}
