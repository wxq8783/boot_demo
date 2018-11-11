package com.wu.im.server.handler;

import com.wu.im.protocol.Packet;
import com.wu.im.protocol.request.GroupMessageRequestPacket;
import com.wu.im.protocol.response.GroupMessageResponsePacket;
import com.wu.im.session.Session;
import com.wu.im.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.Iterator;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {

        GroupMessageResponsePacket packet = new GroupMessageResponsePacket();

        String groupId = msg.getGroupId();

        String message = msg.getMessage();

        Session session = SessionUtil.getSession(ctx.channel());

        if(session == null){
            packet.setSuccess(false);
            packet.setReason("获取session失败");
            ctx.channel().writeAndFlush(packet);
            return;
        }
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if(channelGroup == null){
            packet.setSuccess(false);
            packet.setReason("没有该群");
            ctx.channel().writeAndFlush(packet);
            return;
        }

        packet.setUserId(session.getUserId());
        packet.setUserName(session.getUserName());
        packet.setMessage(message);
        packet.setSuccess(true);
        channelGroup.writeAndFlush(packet);
//        Iterator<Channel> it = channelGroup.iterator();
//        while (it.hasNext()){
//            Channel channel = it.next();
//            if(channel == ctx.channel()){
//                continue;
//            }
//            channel.writeAndFlush(packet);
//        }
//
//        packet.setMessage("发送成功");
//        ctx.channel().writeAndFlush(packet);
    }
}
