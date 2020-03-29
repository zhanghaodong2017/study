package com.zhd.study.nio.znetty07;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyTime7Client {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new NettyTime7Client().connect(port, "127.0.0.1");
    }

    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)//
                    .channel(NioSocketChannel.class)//
                    .option(ChannelOption.TCP_NODELAY, true)//
                    .handler(new ChannelInitializer() {//

                        @Override
                        public void initChannel(Channel ch) throws Exception {
                            // Marshalling 解码器
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            // Marshalling 编码器
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            ch.pipeline().addLast(new TimerClient7Handler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture f = bootstrap.connect(host, port).sync();

            // 等待客户端链路关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }
}

