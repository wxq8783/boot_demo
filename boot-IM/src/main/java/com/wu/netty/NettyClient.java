package com.wu.netty;

import com.wu.netty.handler.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    public static final int MXT_RETRY = 5;
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup workGroup = new NioEventLoopGroup();

        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("++++++++++");
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("first",new FirstClientHandler());
                    }
                });
        doConnect(bootstrap,"127.0.0.1",8989,MXT_RETRY);
    }

    public static void doConnect(final Bootstrap bootstrap,final String address , final int port , final int retry){
        bootstrap.connect(address,port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("++++++++++++++链接成功");
                }else if(retry == 0){
                    System.out.println("+++++++++++++++重试次数已用完");
                }else {
                    int index = (MXT_RETRY - retry) +1 ;
                    int delay = 1 << index;
                    System.out.println(new Date()+":链接失败，第"+retry+"重试");
                    bootstrap.config().group().schedule(()->doConnect(bootstrap,address,port,retry-1),delay,TimeUnit.SECONDS);
                }
            }
        });
    }
}
