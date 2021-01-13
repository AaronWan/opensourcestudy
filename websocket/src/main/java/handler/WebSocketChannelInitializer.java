package handler;

import handler.model.RequestModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        pipeline.addLast(new HttpServerCodec());
        //以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
        pipeline.addLast(new HttpObjectAggregator(8192));

        //ws://server:port/context_path
        //ws://localhost:9999/ws
        //参数指的是contex_path
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //websocket定义了传递数据的6中frame类型
        pipeline.addLast(new SimpleChannelInboundHandler<TextWebSocketFrame>() {
            //每个channel都有一个唯一的id值
            @Override
            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                //打印出channel唯一值，asLongText方法是channel的id的全名
                System.out.println("handlerAdded：" + ctx.channel().id().asLongText());
            }

            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                System.out.println("handlerRemoved：" + ctx.channel().id().asLongText());
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.println("异常发生");
                ctx.close();
            }

            //读到客户端的内容并且向客户端去写内容
            @Override
            protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
                String content= msg.text();

                /**
                 * writeAndFlush接收的参数类型是Object类型，但是一般我们都是要传入管道中传输数据的类型，比如我们当前的demo
                 * 传输的就是TextWebSocketFrame类型的数据
                 */
                ctx.writeAndFlush(new TextWebSocketFrame("me:"+content));
            }
        });
    }
}