package com.zhd.study.nio.znettyCustom;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-30 14:18
 */
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(msg);
        System.out.println("编码后长度：" + bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
