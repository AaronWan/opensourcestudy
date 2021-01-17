package com.netty.study.server.handler.proto;

import com.netty.study.server.codec.CodecFactory;
import com.netty.study.server.model.ServiceRequest;
import com.netty.study.server.model.ServiceResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:29
 * @since 7.3.5
 */
public class ByteToMessageHandler extends ByteToMessageCodec<ServiceResponse> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, ServiceResponse serviceResponse, ByteBuf byteBuf) throws Exception {
    ByteBuf resp = Unpooled.copiedBuffer(CodecFactory.getInstance(CodecFactory.CodeCType.JSON).encode(serviceResponse));
    channelHandlerContext.writeAndFlush(resp);
  }

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
    ServiceRequest serviceRequest = new ServiceRequest();
    ByteBuf buf = byteBuf;
    int methodNameLength=buf.readInt();
    ByteBuf bytes = buf.readBytes(methodNameLength);

    String methodName = new String(bytes.array(), StandardCharsets.UTF_8);

    byte[] req = new byte[buf.readableBytes()];
    buf.readBytes(req);
    serviceRequest.setServiceMethod(methodName);
    serviceRequest.setBody(req);
    list.add(serviceRequest);
  }
}
