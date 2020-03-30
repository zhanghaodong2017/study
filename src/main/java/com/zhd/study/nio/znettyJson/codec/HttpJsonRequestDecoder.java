package com.zhd.study.nio.znettyJson.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.stomp.StompHeaders.CONTENT_TYPE;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:20
 * Modified by :
 */
public class HttpJsonRequestDecoder extends MessageToMessageDecoder<FullHttpRequest> {


    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List list) throws Exception {
        if (!msg.decoderResult().isSuccess()) {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        Map<String, String> map = new HashMap<>();
        String uri = sanitizeUri(msg.uri());
        System.out.println(uri);

        if (uri.contains("?")) {
            String params = uri.substring(uri.indexOf("?") + 1);
            String[] split = params.split("&");
            for (int i = 0; i < split.length; i++) {
                String[] paramArr = split[i].split("=");
                map.put(paramArr[0], paramArr[1]);
            }
        }
        HttpJsonRequest request = new HttpJsonRequest(msg, map);
        list.add(request);
    }

    private String sanitizeUri(String uri) {
        try {
            //使用UTF-8对URL进行解码
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (Exception e) {
            try {
                //解码失败就使用ISO-8859-1进行解码
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            } catch (Exception e2) {
                //仍然失败就返回错误
                throw new Error();
            }
        }

        //将硬编码的文件路径分隔符替换为本地操作系统的文件路径分隔符
        uri = uri.replace('/', File.separatorChar);
        if (uri.contains(File.separator + ".") || uri.contains('.' + File.separator) ||
                uri.startsWith(".") || uri.endsWith(".")) {
            return null;
        }
        //使用当前运行程序所在的工程目录+URI构造绝对路径
        return uri;
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
