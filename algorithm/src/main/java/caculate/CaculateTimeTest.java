package caculate;

import com.google.common.base.Stopwatch;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class CaculateTimeTest {
    private static Random random=new Random(1000);
    public static double timeTrial(int n){
        Stopwatch stopwatch=Stopwatch.createStarted();
        for(int i=0;i<n;i++)
        random.nextInt(1000000);

        return stopwatch.elapsed(TimeUnit.MICROSECONDS);
    }

    public static void main(String[] args) {
        timeTrial(100000);
        for (int i = 1; i < 20; i++) {
            int n=100000*i;
            double t=timeTrial(n);
            System.out.println(t+"MICROSECONDS "+t/Math.pow(n,3));

        }
    }
}
