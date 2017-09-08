package sort;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public interface ISort<T> {
    default void exch(Comparable<T>[] objects, int curr, int target) {
        Comparable temp=objects[curr];
        objects[curr]=objects[target];
        objects[target]=temp;
    }

    default int less(Comparable<T> c1, Comparable<T> c2) {
        return c1.compareTo((T) c2);
    }

    void sort(Comparable<T>[] objects);

    default boolean isSorted(Comparable<T>[] objects) {
        throw new RuntimeException(" not impl");
    }
}
