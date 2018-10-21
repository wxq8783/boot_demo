package com.wu.netty;

import com.wu.netty.handler.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        System.out.println("-----------------");
                        nioSocketChannel.pipeline().addLast("first",new FirstServerHandler());
                    }
                });
        doBind(bootstrap ,8989);
    }


    public static void doBind(final ServerBootstrap bootstrap ,final int port){
        bootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("-------------------绑定端口成功："+port);
                }else{
                    System.out.println("+++++++++++++++++++绑定端口失败，继续重试下个端口号");
                    doBind(bootstrap,port+1);
                }
            }
        });
    }
}
