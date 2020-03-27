package com.zhd.study.nio.znetty03;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 18:00
 */
public class TimerServer3Handler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String reqBody = (String) msg;
        System.out.println("客户端请求:" + reqBody);

        String rspBody = "你好我收到了你的请求-" + reqBody + "&;";
        ByteBuf rspBuf = Unpooled.copiedBuffer(rspBody.getBytes("utf-8"));
        ctx.writeAndFlush(rspBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
