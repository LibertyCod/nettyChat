package com.liberty.server;

import com.liberty.decoder.MessageDecoder;
import com.liberty.encoder.MessageEncoder;
import com.liberty.server.helper.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;


/**
 * @author Binbin Wang
 * @date 2018/1/10
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        socketChannel.pipeline().addLast("msgpack decoder", new MessageDecoder());
        socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
        socketChannel.pipeline().addLast("msgpack encoder", new MessageEncoder());
        socketChannel.pipeline().addLast("server handler", new ServerHandler());
    }
}
