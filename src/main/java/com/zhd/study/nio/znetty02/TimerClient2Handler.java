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
        String rspBody = (String) msg;
        System.out.println("服务端返回>>>" + rspBody);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try {
            for (int i = 0; i < 20; i++) {
                String reqBody = "你好server，我是client" + i + System.getProperty("line.separator");
                byte[] reqBytes = reqBody.getBytes("utf-8");
                ByteBuf rspBuf = Unpooled.buffer(reqBytes.length);
                rspBuf.writeBytes(reqBytes);

                ctx.writeAndFlush(rspBuf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
