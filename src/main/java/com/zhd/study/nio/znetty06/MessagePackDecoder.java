package com.zhd.study.nio.znetty06;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/28 23:10
 * Modified by :
 */
public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int lenth = byteBuf.readableBytes();
        final byte[] bytes = new byte[lenth];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, lenth);
        MessagePack messagePack = new MessagePack();
        MsgDTO dto = messagePack.read(bytes, MsgDTO.class);
        list.add(dto);
    }
}
