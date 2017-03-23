package opensource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aaron on 15/03/2017.
 */
public class RegexTest {
    //    "activity_8##AccountObj##total_refund_amount"
//    "activity_8##AccountObj"
    private static Pattern exp = Pattern.compile("([^\"]*[0-9a-zA-Z]{1,}##[^\"]*)");

    public static void main(String[] args) {
        String example1="lklk\"activity_8##AccountObj##total_refund_amount\""+"sdfdsfdsfds"+"\"activity_8##AccountObj\"";
        Matcher matcher=exp.matcher(example1);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }

}
