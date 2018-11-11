package com.wu.im.client.handler;

import com.wu.im.protocol.response.LogoutResponsePacket;
import com.wu.im.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        if(msg.isSuccess()){
            SessionUtil.unBingSession(ctx.channel());
            System.out.println(msg.getUserName()+"退出成功");
        }else{
            System.out.println("退出失败");
        }
    }
}
