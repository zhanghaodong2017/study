package com.zhd.study.nio.znetty02;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 18:00
 */
public class TimerClient2Handler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf rspBuf = (ByteBuf) msg;
        byte[] rspBytes = new byte[rspBuf.readableBytes()];
        rspBuf.readBytes(rspBytes);
        String rspBody = new String(rspBytes, "utf-8");
        System.out.println("服务端返回>>>" + rspBody);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 20; i++) {
            String reqBody = "你好server，我是client" + i;
            ByteBuf rspBuf = Unpooled.buffer(reqBody.length());
            rspBuf.writeBytes(reqBody.getBytes("utf-8"));

            ctx.writeAndFlush(rspBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
