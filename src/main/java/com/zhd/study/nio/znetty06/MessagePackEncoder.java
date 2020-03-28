package com.zhd.study.nio.znetty06;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/28 23:08
 * Modified by :
 */
public class MessagePackEncoder extends MessageToByteEncoder<MsgDTO> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MsgDTO msgDTO, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(msgDTO);
        byteBuf.writeBytes(bytes);
    }
}
