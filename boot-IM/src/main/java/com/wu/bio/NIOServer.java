package com.wu.bio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            Selector clientSelector = Selector.open();
            new Thread(()->{

                try {
                    ServerSocketChannel socketChannel = ServerSocketChannel.open();
                    socketChannel.bind(new InetSocketAddress(8980));
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_ACCEPT);
                    while(true){
                        //监测是否有新链接
                        if(selector.select(1) > 0){
                            Set<SelectionKey> keySet = selector.selectedKeys();
                            Iterator<SelectionKey> iterator = keySet.iterator();
                            while (iterator.hasNext()){
                                SelectionKey selectionKey = iterator.next();

                                if(selectionKey.isAcceptable()){
                                    // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                    try {
                                        SocketChannel clientChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                                        clientChannel.configureBlocking(false);
                                        clientChannel.register(clientSelector,SelectionKey.OP_READ);
                                    } finally {
                                        iterator.remove();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();




            new Thread(()->{
                try {
                    while(true){
                        if(clientSelector.select(1)>0){
                            Set<SelectionKey> keySet = clientSelector.selectedKeys();
                            Iterator<SelectionKey> iterator = keySet.iterator();
                            while (iterator.hasNext()){
                                SelectionKey selectionKey = iterator.next();
                                if(selectionKey.isReadable()){
                                    try {
                                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                        channel.read(byteBuffer);
                                        byteBuffer.flip();
                                        System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                    } finally {
                                        iterator.remove();
                                        selectionKey.interestOps(SelectionKey.OP_READ);
                                    }

                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();





        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
