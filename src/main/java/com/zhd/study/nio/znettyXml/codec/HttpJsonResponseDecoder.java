package com.zhd.study.nio.znettyXml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http2.HttpUtil;
import io.netty.handler.codec.rtsp.RtspHeaderNames;
import io.netty.handler.codec.rtsp.RtspHeaderValues;

import java.net.InetAddress;
import java.util.List;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:20
 * Modified by :
 */
public class HttpJsonResponseDecoder extends AbstractHttpJsonDecoder<FullHttpResponse> {
    public HttpJsonResponseDecoder(Class clazz) {
        super(clazz);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpResponse msg, List list) throws Exception {
        System.out.println("开始解码...");
        HttpJsonResponse response = new HttpJsonResponse(msg, decode0(ctx, msg.content()));
        list.add(response);
    }
}
