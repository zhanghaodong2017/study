package com.zhd.study.nio.znetty07;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyTime7Server {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new NettyTime7Server().bind(port);
    }

    public void bind(int port) throws Exception {

        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)//
                    .channel(NioServerSocketChannel.class)//
                    .option(ChannelOption.SO_BACKLOG, 100)//
                    .handler(new LoggingHandler(LogLevel.INFO))//
                    .childHandler(new ChannelInitializer() {//

                        @Override
                        public void initChannel(Channel ch) {

                            // 通过工厂类创建MarshallingEncoder解码器，并添加到ChannelPipeline.
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());

                            // 通过工厂类创建MarshallingEncoder编码器，并添加到ChannelPipeline中。
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            ch.pipeline().addLast(new TimerServer7Handler());
                        }
                    });

            // 绑定端口，同步等待成功
            ChannelFuture f = serverBootstrap.bind(port).sync();

            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();

        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
