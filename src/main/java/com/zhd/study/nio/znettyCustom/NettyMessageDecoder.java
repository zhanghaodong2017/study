package com.zhd.study.nio.znettyCustom;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-30 14:18
 */
public class NettyMessageDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        int lenth = byteBuf.readableBytes();
        final byte[] bytes = new byte[lenth];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, lenth);
        MessagePack messagePack = new MessagePack();
        NettyMessage message = messagePack.read(bytes, NettyMessage.class);
        out.add(message);
    }
}
