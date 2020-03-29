package com.zhd.study.nio.znettyXml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http2.HttpUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.OK;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:20
 * Modified by :
 */
public class HttpJsonResponseEncoder extends AbstractHttpJsonEncoder<HttpJsonResponse> {
    @Override
    protected void encode(ChannelHandlerContext ctx, HttpJsonResponse msg, List list) throws Exception {
        //编码
        ByteBuf body = encode0(ctx, msg.getResult());
        FullHttpResponse response = msg.getHttpResponse();
        if (response == null) {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, body);
        } else {
            response = new DefaultFullHttpResponse(msg.getHttpResponse().protocolVersion(), msg.getHttpResponse().status(), body);
        }
        response.headers().set(CONTENT_TYPE, "text/json");
        HttpHeaderUtil.setContentLength(response, body.readableBytes());
        list.add(response);
    }
}
