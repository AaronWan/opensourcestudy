package sort;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import common.RandomDataUtil;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class CompareSortTest {
    @Test
    public void testSort() {
        Integer[] data = RandomDataUtil.createIntArrays(10000);
        @AllArgsConstructor
        class SortBean implements Comparable<SortBean> {
            String name;
            long time;

            @Override
            public int compareTo(SortBean o) {
                return this.time - o.time > 0 ? 1 : -1;
            }

            @Override
            public String toString() {
                final StringBuffer sb = new StringBuffer();
                sb.append("\n").append(name).append("\t").append(time).append(" milliseconds");
                sb.append('\n');
                return sb.toString();
            }
        }

        List<ISort> sorts = Lists.newArrayList(new Selection(), new Insertion());
        SortBean[] sortBeans = new SortBean[sorts.size()];
        for (int i = 0; i < sorts.size(); i++) {
            ISort sort = sorts.get(i);
            Stopwatch stopwatch = Stopwatch.createStarted();
            sort.sort(data);
            sortBeans[i] = new SortBean(sort.getClass().getSimpleName(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }


        new Insertion<SortBean>().sort(sortBeans);

        System.out.println(Arrays.toString(sortBeans));
    }
}
