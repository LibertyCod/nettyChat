package com.liberty.client;

import com.liberty.constant.ServerConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Binbin Wang
 * @date 2018/1/10
 */
public class ClientMain {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        NioEventLoopGroup sendGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(sendGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .handler(new ClientInitializer());
        ChannelFuture future;
        try {
            future = b.connect(ServerConstants.HOST, ServerConstants.PORT).sync();
            System.out.println("connected...");
            //等待服务器socket关闭
            //在本例子中不会发生,这时可以关闭服务器了
            future.channel().closeFuture().sync();
            System.out.println("closed...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sendGroup.shutdownGracefully();
        }
    }
}
