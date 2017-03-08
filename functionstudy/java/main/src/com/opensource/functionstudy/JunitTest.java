package com.opensource.functionstudy;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by Aaron on 08/03/2017.
 */
public class JunitTest {
    @Test
    public void testFunction(){
        List<Person> persons= Lists.newArrayList();
        TestFunction<Person> testFunction = person -> persons.add(person);

        addPerson(testFunction);
        addPerson(testFunction);
        addPerson(testFunction);

        System.out.println(persons.toArray());
    }

    private void addPerson(TestFunction<Person> testFunction) {
        testFunction.callTest(new Person());
    }

    /**
     * 处理完事后  告诉  调用处
     */
    @Test
    public void testConsumer(){
        BiConsumer consumer= (BiConsumer<Person, Person>) (person, person2) -> System.out.println(2);
        consumer.andThen((a,b)-> System.out.println(1)).accept(new Person(),new Person());
    }

    @Test
    public void testPredicate(){
        BiPredicate<Person,Person> biPredicate= (person, person2) -> person.equals(person2);

        biPredicate.and(biPredicate);
        biPredicate.or(biPredicate);
        System.out.println(biPredicate.test(new Person(),new Person()));
    }


    @Test
    public void testRealFunction(){
        Function<Person, Person> function = person -> {
            System.out.println(person);
            return person;
        };

        function.apply(new Person());
    }


}
