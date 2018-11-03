package com.wu.im.server;

import com.wu.im.protocol.*;
import com.wu.im.codec.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(requestByteBuf);

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setVersion(packet.getVersion());
            if(valid(loginRequestPacket)){
                System.out.println("校验成功");
                responsePacket.setSuccess(true);
            }else{
                System.out.println("校验失败");
                responsePacket.setSuccess(false);
                responsePacket.setReason("账号密码错误");
            }

            ByteBuf responseBuf = PacketCodec.INSTANCE.encode(ctx.alloc(),responsePacket);

            ctx.channel().writeAndFlush(responseBuf);

        }else if(packet instanceof MessageRequestPacket){
            MessageRequestPacket requestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date()+":收到客户端信息："+requestPacket.getMessage());

            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setMessage("服务端回复【"+requestPacket.getMessage()+"】");
            ByteBuf responseBuf = PacketCodec.INSTANCE.encode(ctx.alloc(),responsePacket);
            ctx.writeAndFlush(responseBuf);
        }

    }


    private boolean valid(LoginRequestPacket packet){
        if("flash".equals(packet.getUsername())){
            return true;
        }
        return false;
    }
}
