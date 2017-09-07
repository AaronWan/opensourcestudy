package caculate;

import com.google.common.base.Strings;
import org.junit.Assert;

import java.util.Stack;

/**
 * @author 万松(Aaron)
 * E.W.Dijkstra
 * todo 完善算术计算的方法
 * @since 5.7
 */
public class CaculateUtil {
    public static void main(String[] args) {
        Assert.assertEquals(210,execute("( 10 + ( 100 + ( 20 * 5 ) ) )"),0);
        Assert.assertEquals(220,execute("( 10 + 10 + ( 100 + ( 20 * 5 ) ) )"),0);
    }
    public static double execute(String expression){
        if(!Strings.isNullOrEmpty(expression)){
            Stack<Double> value=new Stack();
            Stack<String> operate=new Stack();
            for (int i = 0; i < expression.split(" ").length; i++) {
                String temp=expression.split(" ")[i];
                if(temp.equals("+")){
                    operate.push(temp);
                }else if(temp.equals("-")){
                    operate.push(temp);
                }else if(temp.equals("*")){
                    operate.push(temp);
                }else if(temp.equals("/")){
                    operate.push(temp);
                }else if(temp.equals(")")){
                    String op=operate.pop();
                    Double right=value.pop();
                    Double left=value.pop();
                    value.push(op.equals("+")?left+right:op.equals("-")?left-right:op.equals("*")?left*right:op.equals("/")?left/right:0);
                }else if(temp.equals("(")){

                }else{
                    value.push(Double.valueOf(temp));
                }

            }
            return value.pop();
        }

        return 0;
    }
}
