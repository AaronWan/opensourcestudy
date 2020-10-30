package sort;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import common.RandomDataUtil;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class SortCompare {
    public static SortBean[] compare(List<ISort> sorts, int n, TimeUnit unit) {

        SortBean[] sortBeans = new SortBean[sorts.size()];
        for (int i = 0; i < sorts.size(); i++) {
            ISort sort = sorts.get(i);
            Integer[] data = RandomDataUtil.createIntArrays(n);
            Stopwatch stopwatch = new Stopwatch();
            System.out.println("-----------"+sort.getClass().getSimpleName()+"------------");
            sort.sort(data);
            sortBeans[i] = new SortBean(sort.getClass().getSimpleName(), stopwatch.elapsedTime(unit));
        }


        new Insertion<SortBean>().sort(sortBeans);
        return sortBeans;
    }

    @AllArgsConstructor
    static class SortBean implements Comparable<SortBean> {
        String name;
        long time;

        @Override
        public int compareTo(SortBean o) {
            return this.time - o.time > 0 ? 1 : -1;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            sb.append("\n").append(name).append("\t").append(time);
            sb.append('\n');
            return sb.toString();
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(compare(Lists.newArrayList(new Selection(),new Insertion(),new Shell()), 20, TimeUnit.MILLISECONDS)));
    }
}
