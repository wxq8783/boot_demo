package com.wu.im.client.handler;

import com.wu.im.protocol.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        if(msg.isSuccess()){
            System.out.println("用户【"+msg.getUserName()+"】发送的消息:"+msg.getMessage());
        }else{
            System.out.println("发送失败，原因："+msg.getReason());
        }

    }
}
