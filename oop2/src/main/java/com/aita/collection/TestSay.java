package com.aita.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aita on 17/8/27.
 */
public class TestSay {
    public static void main(String[] args) {
        List<ISay> animals=new ArrayList<>();
        animals.add(new Person());
        animals.add(new Dog());
        animals.add(new Chilken());

        for (int i = 0; i < animals.size(); i++) {
            animals.get(i).say();
        }
    }
}
