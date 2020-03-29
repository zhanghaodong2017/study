package com.zhd.study.nio.znettyXml.client;

import com.zhd.study.nio.znettyXml.codec.HttpJsonRequest;
import com.zhd.study.nio.znettyXml.codec.HttpJsonResponse;
import com.zhd.study.nio.znettyXml.pojo.Order;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:58
 * Modified by :
 */
public class HttpJsonClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接上服务器...");
        Order order = new Order();
        order.setOrderId(123);
        HttpJsonRequest request = new HttpJsonRequest(null, order);
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.getClass().getName());
        HttpJsonResponse response = (HttpJsonResponse) msg;
        System.out.println("接收到了数据..." + response.getResult());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
