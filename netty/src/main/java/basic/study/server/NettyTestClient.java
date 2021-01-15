package basic.study.server;

import com.netty.study.server.Server;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/9
 * @creat_time: 22:19
 * @since 7.3.5
 */
public class NettyTestClient {
  public static void main(String[] args) throws Exception {
    new Thread(() -> {
      try {
        Server.start();
      } catch (InterruptedException e) {
      }
    }).start();

    NettyClient client = new NettyClient("localhost", 8080);
    client.start();
    while (true) {
      Thread.sleep(1000);
      client.getChannel().sendMessage("abc");
    }

  }
}
