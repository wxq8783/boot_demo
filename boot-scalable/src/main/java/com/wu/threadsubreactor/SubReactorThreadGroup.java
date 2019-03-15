package com.wu.threadsubreactor;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * nio 线程组;简易的NIO线程组
 */
public class SubReactorThreadGroup {
    private static final AtomicInteger requestCounter = new AtomicInteger();  //请求计数器

    private static final int DEFAULT_NIO_THREAD_COUNT;

    private  final int nioThreadCount;

    private ExecutorService businessExecutePool;

    private SubReactorThread[] nioThreads;

    static {
        DEFAULT_NIO_THREAD_COUNT = 4;
    }

    public SubReactorThreadGroup() {
        this(DEFAULT_NIO_THREAD_COUNT);
    }

    public SubReactorThreadGroup(int threadCount) {
        if(threadCount < 1){
            threadCount = DEFAULT_NIO_THREAD_COUNT;
        }
        businessExecutePool = Executors.newFixedThreadPool(threadCount);

        this.nioThreadCount = threadCount;

        this.nioThreads = new SubReactorThread[threadCount];

        for(int i = 0;i< threadCount;i++){
            this.nioThreads[i] = new SubReactorThread(businessExecutePool);
            this.nioThreads[i].start();
        }

        System.out.println("Nio 线程数量："+threadCount);
    }

    public void dispatch(SocketChannel socketChannel){
        if(socketChannel != null){
            next().register(new NioTask(socketChannel,SelectionKey.OP_READ));
        }
    }

    protected SubReactorThread next(){
        return this.nioThreads[requestCounter.getAndIncrement() % nioThreadCount];
    }
}
