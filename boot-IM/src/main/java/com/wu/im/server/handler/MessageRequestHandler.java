package com.wu.im.server.handler;

import com.wu.im.protocol.request.MessageRequestPacket;
import com.wu.im.protocol.response.MessageResponsePacket;
import com.wu.im.session.Session;
import com.wu.im.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println("收到客户端消息："+msg.getMessage());

        String toUserId = msg.getToUserId();

        MessageResponsePacket responsePacket = new MessageResponsePacket();
        Session session = SessionUtil.getSession(ctx.channel());
        if(session != null){
            responsePacket.setFromUserId(session.getUserId());
            responsePacket.setFromUserName(session.getUserName());
        }

        responsePacket.setMessage(msg.getMessage());
        Channel toUserChannel = SessionUtil.getChannel(toUserId);
        if(toUserChannel != null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(responsePacket);
        }else{
            System.out.println("【"+toUserId+"】不在线，发送失败");
        }


    }
}
