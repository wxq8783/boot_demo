package com.wu.netty.cpt2_1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOThreadServerHandler implements Runnable {

    private int port ;

    private ServerSocketChannel socketChannel ;

    private Selector selector;

    public NIOThreadServerHandler(int port) {
        try {
            port = port;
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port),1024);
            socketChannel.register(selector,SelectionKey.OP_ACCEPT);
            System.out.println("--------------------启动成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private boolean stopFlag = true ;

    @Override
    public void run() {
        while (stopFlag){
            try {
                selector.select(1000);
                Set<SelectionKey> sets = selector.selectedKeys();
                Iterator<SelectionKey> it = sets.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                     key = it.next();
                     it.remove();
                    try {
                        handler(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        if(key != null){
                            key.cancel();
                            if(key.channel()  != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handler(SelectionKey key) throws IOException {
        if(key.isValid()){
            //新接入
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuf = ByteBuffer.allocate(2014);
                int readBytes = sc.read(readBuf);
                if(readBytes > 0){
                    readBuf.flip();
                    byte[] bytes = new byte[readBuf.remaining()];
                    readBuf.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("------------------"+body);

                    byte[] respBytes = "返回数据".getBytes();
                    ByteBuffer writeBuffer = ByteBuffer.allocate(respBytes.length);
                    writeBuffer.put(respBytes);
                    writeBuffer.flip();
                    sc.write(writeBuffer);
                }
            }
        }
    }
}
