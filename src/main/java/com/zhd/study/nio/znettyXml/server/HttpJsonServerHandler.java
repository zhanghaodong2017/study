package com.zhd.study.nio.znettyXml.server;

import com.zhd.study.nio.znettyXml.codec.HttpJsonRequest;
import com.zhd.study.nio.znettyXml.codec.HttpJsonResponse;
import com.zhd.study.nio.znettyXml.pojo.Address;
import com.zhd.study.nio.znettyXml.pojo.Customer;
import com.zhd.study.nio.znettyXml.pojo.Order;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http2.HttpUtil;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.INTERNAL_SERVER_ERROR;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:45
 * Modified by :
 */
public class HttpJsonServerHandler extends SimpleChannelInboundHandler<HttpJsonRequest> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpJsonRequest jsonRequest = (HttpJsonRequest) msg;
        HttpRequest request = jsonRequest.getRequest();
        Order order = (Order) jsonRequest.getBody();
        System.out.println("Http server receive request : " + order);
        dobusiness(order);
        ChannelFuture future = ctx.writeAndFlush(new HttpJsonResponse(null, order));
        if (!isKeepAlive(request)) {
            future.addListener(new GenericFutureListener<Future<? super Void>>() {
                public void operationComplete(Future future) throws Exception {
                    ctx.close();
                }
            });
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, HttpJsonRequest httpJsonRequest) throws Exception {

    }

    private void dobusiness(Order order) {
        Customer customer = new Customer();
        customer.setFirstName("狄");
        customer.setLastName("仁杰");
        List<String> midNames = new ArrayList<String>();
        midNames.add("李元芳");
        customer.setMiddleNames(midNames);
        Address address = new Address();
        address.setCity("洛阳");
        address.setCountry("大唐");
        address.setState("河南道");
        address.setPostCode("123456");
        order.setCustomer(customer);
        order.setAddress(address);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }

    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("失败: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
