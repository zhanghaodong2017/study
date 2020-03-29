package com.zhd.study.nio.znettyXml.client;

import com.zhd.study.nio.znettyXml.codec.HttpJsonRequestEncoder;
import com.zhd.study.nio.znettyXml.codec.HttpJsonResponseDecoder;
import com.zhd.study.nio.znettyXml.pojo.Order;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

import java.net.InetSocketAddress;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:57
 * Modified by :
 */
public class HttpJsonClient {
    public void connect(int port) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast("http-decoder",
                                    new HttpResponseDecoder());
                            ch.pipeline().addLast("http-aggregator",
                                    new HttpObjectAggregator(65536));
                            // json解码器
                            ch.pipeline().addLast("json-decoder", new HttpJsonResponseDecoder(Order.class));
                            ch.pipeline().addLast("http-encoder",
                                    new HttpRequestEncoder());
                            ch.pipeline().addLast("json-encoder",
                                    new HttpJsonRequestEncoder());
                            ch.pipeline().addLast("jsonClientHandler", new HttpJsonClientHandler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture f = b.connect(new InetSocketAddress(port)).sync();

            // 当代客户端链路关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new HttpJsonClient().connect(port);
    }
}
