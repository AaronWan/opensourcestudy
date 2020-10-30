package com.algorithm.collections;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-10-30
 * @creat_time: 13:44
 * @since 6.6
 */
public class FetchRootFile {
  public static void main(String[] args) {
    System.out.println(removeSubfolders(new String[]{"/a","/a/b","/c/d","/c/d/e","/c/f"}));
  }

  public static List<String> removeSubfolders(String[] folder) {
    Arrays.sort(folder);
    List<String> rst = Lists.newArrayList();
    String temp="";
    for (String f : folder) {
      if(temp.equals("")||!((("/")+(f.split("/")[1])).equals(temp))){
        temp=f;
        rst.add(f);
      }
    }
    return rst;
  }
}
