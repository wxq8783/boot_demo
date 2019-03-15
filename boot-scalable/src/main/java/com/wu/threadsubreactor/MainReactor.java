package com.wu.threadsubreactor;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 主Reactor,主要用来处理连接请求的反应堆
 */
public class MainReactor implements Runnable {

    private Selector selector;

    private SubReactorThreadGroup subReactorThreadGroup;

    public MainReactor(SelectableChannel channel) {
        try {
            selector = Selector.open();
            channel.register(selector,SelectionKey.OP_ACCEPT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        subReactorThreadGroup = new SubReactorThreadGroup(4);
    }

    @Override
    public void run() {

        System.out.println("MainReactor is running");

        while (!Thread.interrupted()){
            Set<SelectionKey> ops = null;

            try {
                selector.select(1000);
                ops = selector.selectedKeys();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 处理相关事件
            for(Iterator<SelectionKey> it = ops.iterator();it.hasNext();){
                SelectionKey key = it.next();
                it.remove();
                try{
                    if(key.isAcceptable()){
                        System.out.println("收到客户端请求。。。。");
                        ServerSocketChannel serverSc = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = serverSc.accept();
                        clientChannel.configureBlocking(false);
                        subReactorThreadGroup.dispatch(clientChannel);// 转发该请求
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }

    }
}
