package com.wu.im.server;

import com.wu.im.codec.PacketDecoder;
import com.wu.im.codec.PacketEncoder;
import com.wu.im.codec.Spliter;
import com.wu.im.demo.LifeCycleTestHandler;
import com.wu.im.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

/**
 * 无论是从服务端来看，还是客户端来看，
 * 在 Netty 整个框架里面，一条连接对应着一个 Channel，
 * 这条 Channel 所有的处理逻辑都在一个叫做 ChannelPipeline 的对象里面，
 * ChannelPipeline 是一个双向链表结构，他和 Channel 之间是一对一的关系。...
 *
 */
public class NettyServer {


    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        //nioSocketChannel.pipeline().addLast(new ServerHandler());
                        //nioSocketChannel.pipeline().addLast(new LifeCycleTestHandler());
                        nioSocketChannel.pipeline().addLast(new Spliter());
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
                        nioSocketChannel.pipeline().addLast(new AuthHandler());
                        nioSocketChannel.pipeline().addLast(new LogoutRequestHandler());
                        nioSocketChannel.pipeline().addLast(new CreateGroupRequestHandler());
                        nioSocketChannel.pipeline().addLast(new JoinGroupRequestHandler());
                        nioSocketChannel.pipeline().addLast(new QuitGroupRequestHandler());
                        nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
                        nioSocketChannel.pipeline().addLast(new GroupMessageRequestHandler());
                        nioSocketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        doBind(bootstrap ,8989);
    }


    public static void doBind(final ServerBootstrap bootstrap ,final int port){
        bootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println(new Date()+"绑定端口成功："+port);
                }else{
                    System.out.println("绑定端口失败，继续重试下个端口号");
                    doBind(bootstrap,port+1);
                }
            }
        });
    }
}
