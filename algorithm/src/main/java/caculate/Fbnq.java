package caculate;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/10/31
 * @creat_time: 20:01
 * @since 7.3.5
 */
public class Fbnq {

  public static void main(String[] args) {
    for (double i = 3.0d; i < 10000000; i++) {
      System.out.println(fbnq(i)/fbnq(i+1));
    }
  }

  public static double fbnq(double n){
    if(n==1||n==2){
      return 1.0;
    }
    return fbnq(n-1)+fbnq(n-2);
  }

}
