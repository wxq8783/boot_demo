package com.wu.singlereactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) {
        new Thread(new Reactor()).start();
    }

    private static final class Reactor implements Runnable{

        private static final byte[] b = "hello,服务器收到了你的信息。".getBytes();
        @Override
        public void run() {
            System.out.println("服务端启动成功，等待客户端接入");
            ServerSocketChannel ssc = null;
            Selector selector = null;
            try {
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress("127.0.0.1",9997));
                selector = Selector.open();
                ssc.register(selector,SelectionKey.OP_ACCEPT);

                Set<SelectionKey> ops = null;
                while (true) {
                    try {
                        selector.select(); //如果没有感兴趣的事件到达，阻塞等待
                        ops = selector.selectedKeys();
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }

                    for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext(); ) {
                        SelectionKey key = it.next();
                        it.remove();

                        try {
                            if (key.isAcceptable()) {
                                ServerSocketChannel serverSc = (ServerSocketChannel) key.channel();
                                SocketChannel clientChannel = serverSc.accept();
                                clientChannel.configureBlocking(false);
                                //向选择器注册读事件 客户端想服务商发送数据准备好后，在处理
                                clientChannel.register(selector, SelectionKey.OP_READ);
                                System.out.println("收到客户端的链接请求。。。。");
                            } else if (key.isWritable()) {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                ByteBuffer buf = (ByteBuffer) key.attachment();
                                buf.flip();
                                clientChannel.write(buf);
                                System.out.println("服务端想客户端发送数据。。。。");
                                //重新注册读事件
                                clientChannel.register(selector, SelectionKey.OP_READ);
                            } else if (key.isReadable()) {
                                System.out.println("服务端接收搭配客户端读请求。。。。。");
                                System.out.println(key);
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                System.out.println("clientChannel.isConnected():" + clientChannel.isConnected());
                                System.out.println("clientChannel.isConnectionPending():" + clientChannel.isConnectionPending());
                                System.out.println("clientChannel.isOpen():" + clientChannel.isOpen());
                                System.out.println("clientChannel.finishConnect():" + clientChannel.finishConnect());
                                ByteBuffer buf = ByteBuffer.allocate(1024);
                                System.out.println(buf.capacity());
                                clientChannel.read(buf);
                                buf.put(b);
                                clientChannel.register(selector, SelectionKey.OP_WRITE, buf);
                            } else {
                                System.out.println("------->" + key);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
