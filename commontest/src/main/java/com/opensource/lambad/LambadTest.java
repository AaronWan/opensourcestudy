package com.opensource.lambad;

import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.functions.Function;
import org.junit.Test;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Aaron on 16/9/1.
 */
public class LambadTest {
    @Test
    public void testListStream(){
        ArrayList list=Lists.newArrayList();
        for(int i=0;i<10;i++){
            list.add(i);
        }

        System.out.println(list.stream().collect(Collectors.toList()));

        System.out.println(
                list.stream().map(i->{return i.toString()+10;}
                ).collect(Collectors.toList()));
    }


    @Test
    public void add(){
        System.out.println(((AddOperator) (a, b) -> a + b).add(20,40));
    }

    @FunctionalInterface
    public interface AddOperator{
        int add(int a,int b);
    }
    public boolean isEven(int i){
        return i%2==0;
    }
    @Test
    public void testIntStream(){
        System.out.println(IntStream.range(0,199).map(i->i*10).average().getAsDouble());
        System.out.println(IntStream.range(17,199).filter(this::isEven).map(i->i).findFirst()
                );
    }
    @Test
    public void listToMap(){
        System.out.println(Lists.newArrayList(100,200,10,30,50).stream().collect(Collectors.toMap(i->i,i->i)));
    }
}
