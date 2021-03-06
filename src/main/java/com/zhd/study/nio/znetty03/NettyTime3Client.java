package com.zhd.study.nio.znetty03;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 19:15
 */
public class NettyTime3Client {
    public static void main(String[] args) {
        new NettyTime3Client().connect("127.0.0.1", 8080);
    }

    public void connect(String host, int port) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            ByteBuf byteBuf1 = Unpooled.copiedBuffer("&;".getBytes());

                            pipeline.addLast(new DelimiterBasedFrameDecoder(1024, byteBuf1));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new TimerClient3Handler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(host, port).sync();

//            future.channel().closeFuture().await();
            TimeUnit.MILLISECONDS.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
