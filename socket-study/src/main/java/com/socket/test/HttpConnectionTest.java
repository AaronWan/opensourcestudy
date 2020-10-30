package com.socket.test;

import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020-03-30
 * @creat_time: 19:43
 * @since 6.6
 */
public class HttpConnectionTest {
  public static void main(String[] args) throws IOException {
    HttpURLConnection connection = (HttpURLConnection) new URL("http://www.baidu.com").openConnection();
    int i=0;
    do{
      int responseCode = connection.getResponseCode();
      InputStream inputStream;
      if (200 <= responseCode && responseCode <= 299) {
        inputStream = connection.getInputStream();
      } else {
        inputStream = connection.getErrorStream();
      }
      BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
      StringBuilder response = new StringBuilder();
      String currentLine;
      while ((currentLine = in.readLine()) != null) {
        response.append(currentLine);
      }
      in.close();
      System.out.println(response.toString());
    }while (i++<10);

  }
}
