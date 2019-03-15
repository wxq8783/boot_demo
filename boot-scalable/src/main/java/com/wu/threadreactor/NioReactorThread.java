package com.wu.threadreactor;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class NioReactorThread extends Thread{

    private static final byte[] b = "Hello,服务器收到了你的信息。".getBytes();

    private Selector selector;

    private List<SocketChannel> waitRegisterLit = new ArrayList<>(512);

    private ReentrantLock registerLock = new ReentrantLock();

    public NioReactorThread() {
        try{
            this.selector = Selector.open();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void register(SocketChannel socketChannel){
        if(socketChannel != null){
            try {
                registerLock.lock();
                waitRegisterLit.add(socketChannel);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                registerLock.unlock();
            }

        }
    }

    @Override
    public void run() {
        while (true){
            Set<SelectionKey> ops = null;
            try {
                selector.select(1000);
                ops = selector.selectedKeys();
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            //处理相关事件
            for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                SelectionKey key =  it.next();
                it.remove();

                try {
                    if (key.isWritable()) { //向客户端发送请求
                        SocketChannel clientChannel = (SocketChannel)key.channel();
                        ByteBuffer buf = (ByteBuffer)key.attachment();
                        buf.flip();
                        clientChannel.write(buf);
                        System.out.println("服务端向客户端发送数据。。。");
                        //重新注册读事件
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if(key.isReadable()) {  //接受客户端请求
                        System.out.println("服务端接收客户端连接请求。。。");
                        SocketChannel clientChannel = (SocketChannel)key.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        System.out.println(buf.capacity());
                        clientChannel.read(buf);//
                        buf.put(b);
                        clientChannel.register(selector, SelectionKey.OP_WRITE, buf);//注册写事件
                    }
                } catch(Throwable e) {
                    e.printStackTrace();
                    System.out.println("客户端主动断开连接。。。。。。。");
                }

            }
            if(!waitRegisterLit.isEmpty()){
                try {
                    registerLock.lock();
                    for(Iterator<SocketChannel> it = waitRegisterLit.iterator();it.hasNext();){
                        SocketChannel sc = it.next();
                        try {
                            sc.register(selector,SelectionKey.OP_READ);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        it.remove();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    registerLock.unlock();
                }
            }


        }
    }
}
