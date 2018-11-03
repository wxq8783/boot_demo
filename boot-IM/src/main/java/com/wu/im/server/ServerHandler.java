package com.wu.im.server;

import com.wu.im.protocol.LoginRequestPacket;
import com.wu.im.protocol.LoginResponsePacket;
import com.wu.im.protocol.Packet;
import com.wu.im.codec.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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

        }

    }


    private boolean valid(LoginRequestPacket packet){
        if("flash".equals(packet.getUsername())){
            return true;
        }
        return false;
    }
}
