package basic.study.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netty.study.server.service.model.TestMethodBean;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

public class MyClientHandler extends ChannelInboundHandlerAdapter {
  private static final Logger logger = Logger.getLogger(MyClientHandler.class.getName());

  private ChannelHandlerContext ctx;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    //与服务端建立连接后
    System.out.println("client channelActive..");
    this.ctx = ctx;
  }

  public static final Gson gson = new GsonBuilder().create();

  public boolean sendMessage(String msg) {
    TestMethodBean.Arg arg = new TestMethodBean.Arg();
    arg.setData(msg);
    boolean result;
    try {
      ByteBuf msgByte;
      byte[] req = gson.toJson(arg).getBytes();
      msgByte = Unpooled.buffer(req.length+2+"a.b".getBytes().length);
      msgByte.writeInt("a.b".getBytes().length);
      msgByte.writeBytes("a.b".getBytes());
      msgByte.writeBytes(req);
      ctx.writeAndFlush(msgByte);
      result = true;
    } catch (Exception e) {
      // TODO: handle exception
      result = false;
      logger.warning(e.getMessage());
    }
    return result;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("client channelRead..");
    //服务端返回消息后
    ByteBuf buf = (ByteBuf) msg;
    byte[] req = new byte[buf.readableBytes()];
    buf.readBytes(req);
    String body = new String(req, "UTF-8");
    System.out.println("Now is :" + body);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("client exceptionCaught..");
    // 释放资源
    logger.warning("Unexpected exception from downstream:" + cause.getMessage());
    ctx.close();
  }

}
