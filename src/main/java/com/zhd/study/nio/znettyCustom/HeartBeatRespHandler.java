package com.zhd.study.nio.znettyCustom;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-30 14:57
 */
public class HeartBeatRespHandler extends SimpleChannelInboundHandler<NettyMessage> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }
}
