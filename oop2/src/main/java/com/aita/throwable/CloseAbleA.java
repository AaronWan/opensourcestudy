package com.aita.throwable;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class CloseAbleA implements AutoCloseable {
    public CloseAbleA() {
    }

    @Override
    public void close() throws Exception {
        System.out.println("CloseableA");
    }
}
