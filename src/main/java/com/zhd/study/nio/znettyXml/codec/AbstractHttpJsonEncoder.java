package com.zhd.study.nio.znettyXml.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:18
 * Modified by :
 */
public abstract class AbstractHttpJsonEncoder<T> extends MessageToMessageEncoder<T> {

    protected ByteBuf encode0(ChannelHandlerContext ctx, Object body) {
        String jsonStr = JSON.toJSONString(body);
        ByteBuf encodeBuf = Unpooled.copiedBuffer(jsonStr, UTF_8);
        return encodeBuf;
    }

}
