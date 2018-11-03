package com.wu.im.client;

import com.wu.im.protocol.LoginRequestPacket;
import com.wu.im.protocol.LoginResponsePacket;
import com.wu.im.protocol.MessageResponsePacket;
import com.wu.im.protocol.Packet;
import com.wu.im.codec.PacketCodec;
import com.wu.im.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date()+":客户端开始登录");

        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUsername("flash");
        packet.setPassword("pwd");

        ByteBuf buffer = PacketCodec.INSTANCE.encode(ctx.alloc(),packet);

        ctx.channel().writeAndFlush(buffer);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket responsePacket = (LoginResponsePacket) packet;

            if(responsePacket.isSuccess()){
                //添加登录成功的标识
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new Date()+":客户端登录成功");
            }else{
                System.out.println(new Date()+":客户端登录失败，失败原因："+responsePacket.getReason());
            }
        }else if(packet instanceof MessageResponsePacket){
            MessageResponsePacket responsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date()+":收到服务端消息："+responsePacket.getMessage());
        }
    }
}
