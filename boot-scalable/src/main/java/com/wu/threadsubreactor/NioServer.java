package com.wu.threadsubreactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class NioServer {
    private static final int DEFAULT_PORT = 9999;
    private static AtomicInteger times = new AtomicInteger(1);
    public static void main(String[] args) {
        new Thread(new Acceptor()).start();
    }

    private static class Acceptor implements Runnable{
        // main Reactor 线程池，用于处理客户端的连接请求
        private static ExecutorService mainReactor = Executors.newSingleThreadExecutor();

        @Override
        public void run() {
            ServerSocketChannel ssc = null;

            try {
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress("127.0.0.1",DEFAULT_PORT));

                //转发到 MainReactor反应堆
                dispatch(ssc);

                System.out.println("服务端启动成功。。。。。。。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void dispatch(ServerSocketChannel ssc){
            System.out.println("------------------"+times.getAndIncrement());
            mainReactor.submit(new MainReactor(ssc));
        }
    }
}
