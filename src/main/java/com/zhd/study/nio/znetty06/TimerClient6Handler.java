package com.zhd.study.nio.znetty06;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 18:00
 */
public class TimerClient6Handler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgDTO dto = (MsgDTO) msg;
        System.out.println("服务端返回>>>" + dto.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try {
            for (int i = 0; i < 10; i++) {
                MsgDTO msgDTO = new MsgDTO(i, "test" + i, "哈哈哈");
                System.out.println(msgDTO);
                ctx.write(msgDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
