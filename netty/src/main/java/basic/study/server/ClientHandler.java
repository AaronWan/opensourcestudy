package basic.study.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String> {

  //处理服务端返回的数据
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String response) throws Exception {
    System.out.println("接受到server响应数据: " + response);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("连接可用");
    super.channelActive(ctx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }
}
 
 
