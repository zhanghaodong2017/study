package com.zhd.study.nio.znetty02;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 19:15
 */
public class NettyTime2Client {
    public static void main(String[] args) {
        new NettyTime2Client().connect("127.0.0.1", 8080);
    }

    public void connect(String host, int port) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new TimerClient2Handler());
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
