package handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

//websocket长连接示例
public class MyServer {
    public static void main(String[] args) throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        serverBootstrap.group(bossGroup, workerGroup)

                .channel(NioServerSocketChannel.class)

                .handler(new LoggingHandler(LogLevel.INFO))

                .childHandler(new WebSocketChannelInitializer());

        ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();

        channelFuture.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();

        workerGroup.shutdownGracefully();
    }
}