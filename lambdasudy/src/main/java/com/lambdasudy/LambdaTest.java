package com.lambdasudy;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class LambdaTest {
    @Test
    public void test1(){
        List<String> rst= Lists.newArrayList("1000","2000").stream().collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        System.out.println(rst);
    }
    @Test
    public void async() throws ExecutionException, InterruptedException {
        System.out.println(CompletableFuture.supplyAsync(()-> "sf", Executors.newCachedThreadPool()).join());
    }
}
