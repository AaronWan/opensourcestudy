package collections;

import java.util.Iterator;

/**
 * @author 万松(Aaron)
 *         <p>
 *         定容栈
 *         泛型
 *         调整数组大小
 *         对象游离
 *         todo 实现Stack
 * @since 5.7
 */
public class Stack<E> implements Iterable<E> {
    private Object[] array;
    private int count = 0;

    public Stack() {
        array = new Object[1];
    }

    public void push(E item) {
        expendSize();
        array[count++] = item;
    }

    private void expendSize() {
        if (count + 1 >= array.length) {
            Object[] temp = new Object[array.length * 2];
            System.arraycopy(array, 0, temp, 0, array.length);
            array = temp;
        }
    }

    private void releaseSpace(){
        if (count != 0 && count <= array.length / 4.0) {
            Object[] temp = new Object[array.length / 2];
            System.arraycopy(array, 0, temp, 0, count);
            array = temp;
        }
    }

    public E pop() {
        int index = --count;
        Object temp = array[index];
        array[index] = null;
        releaseSpace();
        return (E) temp;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (index++ < count - 1) {
                    return true;
                }
                return false;
            }

            @Override
            public E next() {
                return (E) array[index];
            }
        };
    }
}
