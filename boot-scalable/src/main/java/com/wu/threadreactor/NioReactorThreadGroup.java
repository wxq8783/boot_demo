package com.wu.threadreactor;

import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简易的NIO线程组
 */
public class NioReactorThreadGroup {

    private static final AtomicInteger requestCounter = new AtomicInteger();

    private final int nioThreadCount;

    private static final int DEFAULT_NIO_THREAD_COUNT;

    private NioReactorThread[] nioThreads;

    static {
        DEFAULT_NIO_THREAD_COUNT = 4;
    }

    public NioReactorThreadGroup(){
        this(DEFAULT_NIO_THREAD_COUNT);
    }

    public NioReactorThreadGroup(int threadCount) {
        if(threadCount < 1){
            threadCount = DEFAULT_NIO_THREAD_COUNT;
        }
        this.nioThreadCount = threadCount;
        this.nioThreads = new NioReactorThread[threadCount];
        for(int i = 0;i< threadCount;i++){
            this.nioThreads[i] = new NioReactorThread();
            this.nioThreads[i].start();//构造方法 中启动线程，犹豫nioThreads不会对外暴露
        }
        System.out.println("Nio 线程数量:"+threadCount);
    }

    public void dispatch(SocketChannel socketChannel){
        if(socketChannel != null){
            next().register(socketChannel);
        }
    }

    protected NioReactorThread next(){
        return this.nioThreads[requestCounter.getAndIncrement()%nioThreadCount];
    }

    public static void main(String[] args) {

    }
}
