package com.zhd.study.nio.znettyXml.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONTENT_TYPE;
import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:18
 * Modified by :
 */
public abstract class AbstractHttpJsonDecoder<T> extends MessageToMessageDecoder<T> {
    private Class<?> clazz;

    public AbstractHttpJsonDecoder(Class<?> clazz) {
        this.clazz = clazz;
    }

    protected Object decode0(ChannelHandlerContext ctx, ByteBuf body) {
        String content = body.toString(UTF_8);
        System.out.println("The body is : " + content);
        Object result = JSON.parseObject(content, clazz);
        return result;
    }
    /**
     * 测试的话，直接封装，实战中需要更健壮的处理
     */
    protected void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


}
