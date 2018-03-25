package interview;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 万松(Aaron)
 * @since 6.2
 */
public class CountBits {

    /**
     *
     */
    static class Impl1{
        public static int[] countBits(int n){
            int[] rst=new int[n+1];
            for(int i=0;i<=n;i++){
                if(i==0){
                    rst[i]=0;
                    continue;
                }
                rst[i]=countBit(i);
            }
            return rst;
        }

        private static int countBit(int n) {
            int count=0 ;
            while (n>0)  {
                count++ ;
                n &= (n - 1) ;
            }
            return count ;
        }

        //111
        private static int countBit1(int n) {
            int count=0 ;
            int temp=1;
            while (temp<=n)  {
                if((temp&n)>0){
                    count++ ;
                }
                temp<<=1;
            }
            return count ;
        }

    }

    static Gson gson=new GsonBuilder().create();

    static class Impl2{

        public static int[] countBits(int num) {
            int[] ret = new int[num + 1];

            for (int i = 0; i <= num; i++) {
                int div = i / 2;
                int mod = i % 2;

                if (mod == 1) {
                    ret[i] = ret[div] + 1;
                } else {
                    ret[i] = ret[div];
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        for(int i=1;i<Integer.MAX_VALUE;i++){
            System.out.printf("%d,%d\n",Impl1.countBit(i),Impl1.countBit1(i));
        }
    }
}
