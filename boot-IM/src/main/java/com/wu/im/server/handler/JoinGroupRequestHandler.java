package com.wu.im.server.handler;

import com.wu.im.protocol.request.JoinGroupRequestPacket;
import com.wu.im.protocol.response.JoinGroupResponsePacket;
import com.wu.im.session.Session;
import com.wu.im.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        JoinGroupResponsePacket packet = new JoinGroupResponsePacket();
        String groupId = msg.getGroupId();
        Session session = SessionUtil.getSession(ctx.channel());
        if(session == null){
            packet.setSuccess(false);
            packet.setReason("你还没有登录");
            ctx.channel().writeAndFlush(packet);
            return;
        }
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if(channelGroup == null){
            packet.setSuccess(false);
            packet.setReason("没有这个群");
            ctx.channel().writeAndFlush(packet);
            return;
        }
        List<String> userInfoList = new ArrayList<>();
        Iterator<Channel> it = channelGroup.iterator();
        while (it.hasNext()){
            Session  session1 = SessionUtil.getSession(it.next());
            if(session1 != null){
                userInfoList.add(session1.getUserName());
            }
        }
        channelGroup.add(ctx.channel());
        SessionUtil.bindGroup(groupId,channelGroup);
        packet.setGroupId(groupId);
        packet.setSuccess(true);
        packet.setUserInfoList(userInfoList);
        ctx.channel().writeAndFlush(packet);
    }


}
