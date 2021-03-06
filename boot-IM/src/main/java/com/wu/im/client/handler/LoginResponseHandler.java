package com.wu.im.client.handler;

import com.wu.im.protocol.response.LoginResponsePacket;
import com.wu.im.session.Session;
import com.wu.im.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        String userId = msg.getUserId();
        String userName = msg.getUserName();

        if(msg.isSuccess()){
            SessionUtil.bingSession(new Session(userId,userName),ctx.channel());
            System.out.println("[" + userName + "]登录成功，userId 为: " + msg.getUserId());
        }else{
            System.out.println("[" + userName + "]客户端登录失败，原因："+msg.getReason());
        }
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端链接被关闭");
        super.channelInactive(ctx);
    }
}
