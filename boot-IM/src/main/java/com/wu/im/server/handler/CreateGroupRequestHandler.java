package com.wu.im.server.handler;

import com.wu.im.protocol.request.CreateGroupRequestPacket;
import com.wu.im.protocol.response.CreateGroupResponsePacket;
import com.wu.im.session.SessionUtil;
import com.wu.im.util.IDUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        ChannelGroup group = new DefaultChannelGroup(ctx.executor());

        for(String userId : userIdList){
            Channel channel = SessionUtil.getChannel(userId.trim());
            if(channel != null){
                group.add(channel);
                String userName = SessionUtil.getSession(channel).getUserName();
                userNameList.add(userName);
            }
        }

        //创建群聊的响应结果
        CreateGroupResponsePacket packet = new CreateGroupResponsePacket();
        packet.setSuccess(true);
        packet.setGroupId(IDUtil.randomId());
        packet.setUserNameList(userNameList);
        group.writeAndFlush(packet);

        System.out.print("群创建成功，id 为[" + packet.getGroupId() + "], ");
        System.out.println("群里面有：" + packet.getUserNameList());



    }
}
