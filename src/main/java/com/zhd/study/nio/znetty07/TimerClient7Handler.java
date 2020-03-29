package com.zhd.study.nio.znetty07;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-29 13:09
 */
public class TimerClient7Handler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //在链路激活的时候循环构造10条订购请求消息，最后一次性地发送给服务端。
        for (int i = 0; i < 10; i++) {
            Request req = new Request(i, "zhangsan", "上海");
            System.out.println(req);
            ctx.write(req);
        }
        ctx.flush();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //由于对象解码器已经对订购请求应答消息进行了自动解码，
        //因此，ClientHandler接收到的消息已经是解码成功后的订购应答消息。
        System.out.println("Receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
