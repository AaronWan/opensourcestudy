package com.netty.study.server.handler.proto;

import com.netty.study.server.model.ServiceRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:29
 * @since 7.3.5
 */
public class ByteToMessageHandler extends ByteToMessageCodec<ServiceRequest> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, ServiceRequest serviceRequest, ByteBuf byteBuf) throws Exception {
    System.out.println("....");
  }

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
    System.out.println(byteBuf.getInt(1));
  }
}
