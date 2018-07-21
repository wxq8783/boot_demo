package com.wu.netty.cpt8.server;

import com.wu.netty.cpt8.Header;
import com.wu.netty.cpt8.NettyMessage;
import com.wu.netty.cpt8.MessageType;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class HeartBeatRespHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if(message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()){
            System.out.println("receive client heart beat message : ---->"+message);
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println("send heart beat response message to client : ---->"+heartBeat);
            ctx.writeAndFlush(heartBeat);
        }else{
            ctx.writeAndFlush(msg);
        }
    }

    private NettyMessage buildHeartBeat(){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
