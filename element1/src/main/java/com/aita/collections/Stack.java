package com.aita.collections;

import java.util.Iterator;

/**
 * @author 万松(Aaron)
 *         定容栈
 *         泛型
 *         调整数组大小
 *         对象游离
 * @since 5.7
 */
public class Stack implements Iterable {

    private Object[] data;
    private int count;

    public Stack() {
        data = new Object[1];
        count = 0;
    }

    public void push(Object item) {
        extendSpace();
        data[count++] = item;
    }

    private void extendSpace() {
        if (count + 1 >= data.length) {
            Object[] temp = new Object[data.length * 2];
            System.arraycopy(data, 0, temp, 0, count);
            data = temp;
            System.out.println("extend space " + data.length);
        }
    }

    public Object pop() {
        Object temp = data[--count];
        data[count] = null;
        releaseSpace();
        return temp;
    }

    private void releaseSpace() {
        if (count <= data.length / 4.0) {
            Object[] temp = new Object[data.length / 2];
            System.arraycopy(data, 0, temp, 0, count);
            data = temp;
            System.out.println("release space to " + temp.length);
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public Iterator iterator() {
        return null;
    }
}
