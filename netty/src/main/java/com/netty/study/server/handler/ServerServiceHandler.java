package com.netty.study.server.handler;

import com.netty.study.server.codec.CodecFactory;
import com.netty.study.server.model.ServiceModel;
import com.netty.study.server.model.ServiceRequest;
import com.netty.study.server.register.ServerServiceRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:26
 * @since 7.3.5
 */
public class ServerServiceHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("service  register ......");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("server channelRead..");
    ServiceRequest request = (ServiceRequest) msg;
    ServiceModel serviceModel = ServerServiceRegister.getServiceModel(request.getServiceMethod());
    Object arg = CodecFactory.getInstance(CodecFactory.CodeCType.JSON).decode(request.getBody(), serviceModel.getArgClazz());
    ctx.write(serviceModel.invoke(arg));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    super.exceptionCaught(ctx, cause);
  }
}
