package com.aita.throwable;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class CloseAbleB implements AutoCloseable {
    public CloseAbleB() {
    }

    @Override
    public void close() throws Exception {
        System.out.println("CloseableB");
    }
}
