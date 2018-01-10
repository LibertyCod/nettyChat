package com.liberty.server.helper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @author Binbin Wang
 * @date 2018/1/10
 */
public class MessageEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] writedBytes = messagePack.write(byteBuf);
        byteBuf2.writeBytes(writedBytes);
    }
}
