package com.wu.im.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date()+":服务端收到数据-->"+byteBuf.toString(Charset.forName("UTF-8")));

//        ByteBuf out = getByteBuf(ctx,"channelRead");
//        ctx.channel().writeAndFlush(out);
    }


//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf byteBuf = getByteBuf(ctx,"channelActive");
//        ctx.channel().writeAndFlush(byteBuf);
//    }
//
//    public ByteBuf getByteBuf(ChannelHandlerContext ctx,String msg){
//        byte[] bytes = (msg+"你好，欢迎链接我的服务器，有什么需要帮助，请指示").getBytes(Charset.forName("utf-8"));
//        ByteBuf byteBuf = ctx.alloc().buffer();
//        byteBuf.writeBytes(bytes);
//        return byteBuf;
//    }
}
