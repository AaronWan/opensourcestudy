package script;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-09-19
 * @creat_time: 21:26
 * @since 6.6
 */
public class SplitFile {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/Aaron/Desktop/庆余年2天下太平.txt")));
    int count = 0;
    String line = null;
    Pattern pattern= Pattern.compile("第.*章(.*)");
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(count+++" .珠帘篇--《小地瓜，我找到你了》")));
    while ((line = br.readLine()) != null) {
      if(line.length()==0){
        continue;
      }
      Matcher temp = pattern.matcher(line);
      if (temp.matches()) {
        pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(count+++" ."+temp.group(1).trim()+".txt")));
      }
      pw.println(line.replaceAll(" ",""));
      pw.flush();
    }

  }
}
