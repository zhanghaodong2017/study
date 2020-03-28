package com.zhd.study.nio.znetty06;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 18:00
 */
public class TimerServer6Handler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgDTO msgDTO = (MsgDTO) msg;
        System.out.println("客户端请求:" + msgDTO.toString());

        String rspBody = "服务器时间--" + System.currentTimeMillis();
        msgDTO.setData(rspBody);
        ctx.writeAndFlush(msgDTO);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
