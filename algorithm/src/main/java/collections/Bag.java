package collections;

import java.util.Iterator;

/**
 * @author 万松(Aaron)
 *
 * @since 5.7
 */
public class Bag<E> implements Iterable<E> {

    public void add(E item){

    }

    public boolean isEmpty(){

        return false;
    }

    public int size(){

        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public static void main(String[] args) {
    }

}
