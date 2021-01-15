package com.netty.study.server.handler.proto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netty.study.server.model.ServiceResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;


/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:29
 * @since 7.3.5
 */
public class MessageToByteHandler extends MessageToByteEncoder<ServiceResponse> {
  public static final Gson gson=new GsonBuilder().create();
  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, ServiceResponse serviceResponse, ByteBuf byteBuf) throws Exception {
    System.out.println("....");
    byte[] response=gson.toJson(serviceResponse).getBytes(StandardCharsets.UTF_8);
    ByteBuf resp = Unpooled.copiedBuffer(response);
    channelHandlerContext.writeAndFlush(resp);
  }

}
