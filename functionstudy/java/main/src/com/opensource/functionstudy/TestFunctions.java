package com.opensource.functionstudy;

import com.google.common.collect.Lists;
import org.junit.Test;

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

    private void addPerson(TestFunction<Person> testFunction) {
        testFunction.callTest(new Person());
    }

}
