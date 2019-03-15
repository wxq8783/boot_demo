package com.wu.threadreactor;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) {
        new Thread(new Acceptor()).start();
    }

    private static final class Acceptor implements Runnable{

        private final NioReactorThreadGroup nioReactorThreadGroup;

        public Acceptor() {
            this.nioReactorThreadGroup = new NioReactorThreadGroup();
        }

        @Override
        public void run() {
            System.out.println("服务端启动成功，等待客户端接入。。。。");
            ServerSocketChannel ssc = null;
            Selector selector = null;
            try{
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress("127.0.0.1",9998));

                selector = Selector.open();

                ssc.register(selector,SelectionKey.OP_ACCEPT);

                Set<SelectionKey> ops = null;

                while(true){
                    try{
                        selector.select();
                        ops = selector.selectedKeys();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    for(Iterator<SelectionKey> it = ops.iterator();it.hasNext();){
                        SelectionKey key = it.next();
                        it.remove();
                        try{
                            if(key.isAcceptable()){//客户端建立连接
                                System.out.println("收到客户端的链接请求。。。。");
                                ServerSocketChannel serverSc = (ServerSocketChannel) key.channel();
                                SocketChannel clientChannel = serverSc.accept();
                                clientChannel.configureBlocking(false);
                                nioReactorThreadGroup.dispatch(clientChannel);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("客户端主动断开链接。。。。。");
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
