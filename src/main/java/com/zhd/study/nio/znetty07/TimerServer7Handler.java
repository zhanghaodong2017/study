package com.zhd.study.nio.znetty07;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-29 13:02
 */
public class TimerServer7Handler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        System.out.println("客户端请求:" + request.toString());

        String rspBody = "服务器时间--" + System.currentTimeMillis();
        Response response = new Response(request.getSubReqID(), 200, rspBody);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
