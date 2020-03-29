package com.zhd.study.nio.znettyXml.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONTENT_TYPE;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:20
 * Modified by :
 */
public class HttpJsonRequestDecoder extends AbstractHttpJsonDecoder<FullHttpRequest> {

    public HttpJsonRequestDecoder(Class clazz) {
        super(clazz);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List list) throws Exception {
        if (!msg.decoderResult().isSuccess()) {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        HttpJsonRequest request = new HttpJsonRequest(msg, decode0(ctx, msg.content()));
        list.add(request);
    }


}
