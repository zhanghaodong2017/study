package com.zhd.study.nio.znettyJson.server;

import com.zhd.study.nio.znettyJson.codec.HttpJsonRequest;
import com.zhd.study.nio.znettyJson.codec.HttpJsonResponse;
import com.zhd.study.nio.znettyJson.pojo.RspInfo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Map;

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

    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("失败: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpJsonRequest httpJsonRequest) throws Exception {
        HttpRequest request = httpJsonRequest.getRequest();
        Map<String, String> paramsMap = httpJsonRequest.getParams();
        System.out.println("Http server receive request : " + paramsMap);
        RspInfo rspInfo = new RspInfo(0, "原路返回", paramsMap);
        HttpJsonResponse response = new HttpJsonResponse(null, rspInfo);
        ChannelFuture future = ctx.writeAndFlush(response);
        if (!isKeepAlive(request)) {
            future.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future future) throws Exception {
                    ctx.close();
                }
            });
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }
}
