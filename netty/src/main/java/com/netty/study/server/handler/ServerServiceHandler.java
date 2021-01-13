package com.netty.study.server.handler;

import com.netty.study.server.model.ServiceRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/1/13
 * @creat_time: 23:26
 * @since 7.3.5
 */
public class ServerServiceHandler extends ChannelInboundHandlerAdapter {
  /**
   * Calls {@link ChannelHandlerContext#fireChannelRegistered()} to forward
   * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
   * <p>
   * Sub-classes may override this method to change behavior.
   *
   * @param ctx
   */
  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("service  register ......");
  }

  /**
   * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
   * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
   * <p>
   * Sub-classes may override this method to change behavior.
   *
   * @param ctx
   * @param msg
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    super.channelRead(ctx, msg);

  }
}
