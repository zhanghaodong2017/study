package com.zhd.study.nio.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 16:34
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler serverHandler) {
        serverHandler.serverSocketChannel.accept(serverHandler, this);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler serverHandler) {
        exc.printStackTrace();
        serverHandler.countDownLatch.countDown();
    }
}
