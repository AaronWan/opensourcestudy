package com.opensource.functionstudy;

/**
 * Created by Aaron on 08/03/2017.
 */
@FunctionalInterface
public interface TestFunction<T> {
    void callTest(T t);
}
