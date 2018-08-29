package com.wu.chat.server.handler;

import com.wu.chat.process.IMProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WebSocketServerChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private IMProcessor processor = new IMProcessor();

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("------"+ msg.text());
        processor.process(ctx.channel() ,msg.text());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        processor.logout(ctx.channel());

    }
}
