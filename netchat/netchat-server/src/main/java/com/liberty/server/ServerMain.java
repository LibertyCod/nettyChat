package com.liberty.server;

import com.liberty.constant.ServerConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Binbin Wang
 * @date 2018/1/10
 */
public class ServerMain {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        NioEventLoopGroup acceptorGroup = new NioEventLoopGroup();
        NioEventLoopGroup IOGroup = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(acceptorGroup, IOGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ServerInitializer());
        ChannelFuture future;
        try {
            future = sb.bind(ServerConstants.HOST, ServerConstants.PORT).sync();
            System.out.println("server connected...");
            //等待服务器socket关闭
            //在本例子中不会发生,这时可以关闭服务器了
            future.channel().closeFuture().sync();
            System.out.println("server closed...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            IOGroup.shutdownGracefully();
            acceptorGroup.shutdownGracefully();
        }


    }
}
