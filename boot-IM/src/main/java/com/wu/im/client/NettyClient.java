package com.wu.im.client;

import com.wu.im.codec.PacketCodec;
import com.wu.im.protocol.MessageRequestPacket;
import com.wu.im.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    public static final int MXT_RETRY = 5;
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup workGroup = new NioEventLoopGroup();

        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ClientHandler());
                    }
                });
        doConnect(bootstrap,"127.0.0.1",8989,MXT_RETRY);
    }

    public static void doConnect(final Bootstrap bootstrap,final String address , final int port , final int retry){
        bootstrap.connect(address,port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println(new Date()+":链接成功，启动控制台...");
                    Channel channel = ((ChannelFuture)future).channel();
                    startConsoleThread(channel);
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

    private static void startConsoleThread(Channel channel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()){
                    if(LoginUtil.hasLogin(channel)){
                        System.out.println("输入消息发送至服务端:");
                        Scanner sc = new Scanner(System.in);
                        String line = sc.nextLine();

                        MessageRequestPacket packet = new MessageRequestPacket();
                        packet.setMessage(line);
                        ByteBuf byteBuf = PacketCodec.INSTANCE.encode(channel.alloc(),packet);
                        channel.writeAndFlush(byteBuf);
                    }else{
                        //System.out.println("登录失败");
                    }
                }
            }
        }).start();
    }


}
