package com.opensource.stream;

import com.opensource.functionstudy.Person;

import java.util.stream.Stream;

/**
 * Created by Aaron on 23/03/2017.
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream.of(new Person(),new Person(),new Person()).forEach(person -> {
            System.out.println(person.toString());
        });
    }
}
