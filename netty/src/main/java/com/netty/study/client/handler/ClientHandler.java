package com.netty.study.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ClientHandler extends ChannelInboundHandlerAdapter {
  private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

  private ChannelHandlerContext ctx;
  private byte[] result;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    //与服务端建立连接后
    System.out.println("client channelActive..");
    this.ctx = ctx;
  }

  public byte[] sendMessage(String service,byte[] data) {
    try {
      ByteBuf msgByte;
      msgByte = Unpooled.buffer(data.length+2+service.getBytes().length);
      msgByte.writeInt(service.getBytes().length);
      msgByte.writeBytes(service.getBytes());
      msgByte.writeBytes(data);
      ctx.writeAndFlush(msgByte).sync();
    } catch (Exception e) {
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
    result=req;
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("client exceptionCaught..");
    // 释放资源
    logger.warning("Unexpected exception from downstream:" + cause.getMessage());
    ctx.close();
  }

}
