package handler;

import com.google.common.collect.Maps;
import handler.model.RequestModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;
import java.util.Objects;

//处理文本协议数据，处理TextWebSocketFrame类型的数据，websocket专门处理文本的frame就是TextWebSocketFrame
public class RequestWebSocketFrameHandler extends SimpleChannelInboundHandler<RequestModel> {
    public static final Map<String, ChannelHandlerContext> handlerContextMap = Maps.newConcurrentMap();

    //每个channel都有一个唯一的id值
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //打印出channel唯一值，asLongText方法是channel的id的全名
        System.out.println("handlerAdded：" + ctx.channel().id().asLongText());
        handlerContextMap.put(ctx.channel().id().asLongText(), ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生");
        handlerContextMap.remove(ctx.channel().id().asLongText());
        ctx.close();
    }

    //读到客户端的内容并且向客户端去写内容
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, RequestModel msg) throws Exception {
        ctx.writeAndFlush(new RequestModel());
    }
}