package com.netty.study.client;

import com.netty.study.client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/9
 * @creat_time: 22:54
 * @since 7.3.5
 */
public class NettyClient {
  private ClientHandler client;
  private final EventLoopGroup group = new NioEventLoopGroup();

  //连接服务端的端口号地址和端口号
  public NettyClient(String host, int port) {
    init(host,port);
  }

  public void init(String host,int port) {
    try {
      client = new ClientHandler();
      Bootstrap b = new Bootstrap();
      b.group(group).channel(NioSocketChannel.class)  // 使用NioSocketChannel来作为连接用的channel类
        .handler(new ChannelInitializer<SocketChannel>() { // 绑定连接初始化器
          @Override
          public void initChannel(SocketChannel ch) throws Exception {
            System.out.println("正在连接中...");
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(client); //客户端处理类

          }
        });
      //发起异步连接请求，绑定连接端口和host信息
      final ChannelFuture future = b.connect(host, port).sync();

      future.addListener((ChannelFutureListener) arg0 -> {
        if (future.isSuccess()) {
          System.out.println("连接服务器成功");

        } else {
          System.out.println("连接服务器失败");
          future.cause().printStackTrace();
          group.shutdownGracefully(); //关闭线程组
        }
      });
    } catch (Exception e) {

    }
  }

  public ClientHandler getChannel() {
    return client;
  }
}
