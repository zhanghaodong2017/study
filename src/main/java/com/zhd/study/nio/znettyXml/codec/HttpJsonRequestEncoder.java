package com.zhd.study.nio.znettyXml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http2.HttpUtil;
import io.netty.handler.codec.rtsp.RtspHeaderValues;

import java.net.InetAddress;
import java.util.List;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:19
 * Modified by :
 */
public class HttpJsonRequestEncoder extends AbstractHttpJsonEncoder<HttpJsonRequest> {
    @Override
    protected void encode(ChannelHandlerContext ctx, HttpJsonRequest msg, List list) throws Exception {
        //(1)调用父类的encode0，将业务需要发送的对象转换为Json
        ByteBuf body = encode0(ctx, msg.getBody());
        //(2) 如果业务自定义了HTTP消息头，则使用业务的消息头，否则在这里构造HTTP消息头
        // 这里使用硬编码的方式来写消息头，实际中可以写入配置文件
        FullHttpRequest request = msg.getRequest();
        if (request == null) {
            request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                    HttpMethod.GET, "/do", body);
            HttpHeaders headers = request.headers();
            headers.set(HttpHeaderNames.HOST, InetAddress.getLocalHost()
                    .getHostAddress());
            headers.set(HttpHeaderNames.CONNECTION, RtspHeaderValues.CLOSE);
            headers.set(HttpHeaderNames.ACCEPT_ENCODING,
                    HttpHeaderValues.GZIP.toString() + ','
                            + HttpHeaderValues.DEFLATE.toString());
            headers.set(HttpHeaderNames.ACCEPT_CHARSET,
                    "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
            headers.set(HttpHeaderNames.ACCEPT_LANGUAGE, "zh");
            headers.set(HttpHeaderNames.USER_AGENT,
                    "Netty json Http Client side");
            headers.set(HttpHeaderNames.ACCEPT,
                    "text/html,application/json;q=0.9,*/*;q=0.8");
        }
        HttpHeaderUtil.setContentLength(request, body.readableBytes());
        // (3) 编码后的对象
        list.add(request);
    }
}
