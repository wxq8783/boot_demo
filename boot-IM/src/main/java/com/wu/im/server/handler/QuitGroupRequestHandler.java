package com.wu.im.server.handler;

import com.wu.im.protocol.request.QuitGroupRequestPacket;
import com.wu.im.protocol.response.QuitGroupResponsePacket;
import com.wu.im.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.Iterator;

public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        QuitGroupResponsePacket packet = new QuitGroupResponsePacket();
        String groupId = msg.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if(channelGroup == null){
            packet.setSuccess(false);
            packet.setReason("群已经不存在");
            ctx.channel().writeAndFlush(packet);
            return;
        }
        if(channelGroup.remove(ctx.channel())){
            packet.setSuccess(true);
            packet.setGroupId(groupId);
        }else{
            packet.setSuccess(false);
            packet.setReason("退出失败");
        }
        ctx.channel().writeAndFlush(packet);

    }
}
