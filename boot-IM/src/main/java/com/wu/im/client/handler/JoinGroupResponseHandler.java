package com.wu.im.client.handler;

import com.wu.im.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if(msg.isSuccess()){
            System.out.println("群【"+msg.getGroupId()+"】，群里有【"+msg.getUserInfoList()+"】");
        }else{
            System.out.println("加入失败，原因是:"+msg.getReason());
        }
    }
}
