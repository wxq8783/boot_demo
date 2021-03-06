package com.wu.im.server.handler;

import com.wu.im.protocol.request.LoginRequestPacket;
import com.wu.im.protocol.response.LoginResponsePacket;
import com.wu.im.session.Session;
import com.wu.im.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date()+":收到客户端登录请求.......");

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(loginRequestPacket.getVersion());
        if(valid(loginRequestPacket)){
            String userId = randomUserId();
            SessionUtil.bingSession(new Session(userId,loginRequestPacket.getUsername()),ctx.channel());
            responsePacket.setUserId(userId);
            responsePacket.setUserName(loginRequestPacket.getUsername());
            responsePacket.setSuccess(true);
            System.out.println("【"+loginRequestPacket.getUsername()+"】登录成功");
        }else{
            responsePacket.setSuccess(false);
            responsePacket.setReason("账号密码校验失败");
            System.out.println(new Date()+"：登录失败！");
        }
        ctx.channel().writeAndFlush(responsePacket);


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBingSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket packet){
        return true;
    }


    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
