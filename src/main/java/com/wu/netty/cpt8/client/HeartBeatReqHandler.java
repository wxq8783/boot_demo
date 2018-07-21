package com.wu.netty.cpt8.client;

import com.wu.netty.cpt8.Header;
import com.wu.netty.cpt8.MessageType;
import com.wu.netty.cpt8.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    private volatile ScheduledFuture<?> heartbeat ;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        if(message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()){
            heartbeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx) ,0 ,5000 , TimeUnit.MILLISECONDS);
        }else if(message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()){
            System.out.println("Client receive server heart beat message :----> "+message);
        }else {
            ctx.fireChannelRead(msg);
        }
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx ;
        public HeartBeatTask(final  ChannelHandlerContext ctx){
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heartBeat = buildHeatbeat();
            System.out.println("Client send heart beat message to server : ---->"+heartBeat);
            ctx.writeAndFlush(heartBeat);
        }
    }

    private NettyMessage buildHeatbeat(){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_REQ.value());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(heartbeat != null){
            heartbeat.cancel(true);
            heartbeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}
