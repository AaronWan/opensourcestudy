package common;


import java.util.Random;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class RandomDataUtil {

    public static <E> E[] createArrays(int n, BeanCreator<E> creator) {
        E[] e = (E[]) new Object[n];
        while (n > 0) {
            e[--n] = creator.createBean(n);
        }
        return e;
    }

    interface BeanCreator<E> {
        E createBean(int i);
    }

    static Random random = new Random();

    public static Integer[] createIntArrays(int n) {
        Integer[] rst = new Integer[n];
        while (n > 0) {
            rst[--n] = random.nextInt(10000);
        }
        return rst;
    }

}
