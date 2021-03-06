package com.wu.proactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

public class Peactor implements Runnable {
    final Selector selector;

    final ServerSocketChannel serverSocket;

    Peactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector,SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator iterator = selected.iterator();
                while (iterator.hasNext()){
                    dispatch((SelectionKey)iterator.next());
                }
                selected.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    void dispatch(SelectionKey k){
        Runnable r = (Runnable) k.attachment();
        if(r != null){
            r.run();
        }
    }

    class Acceptor implements Runnable{

        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if(c != null){
                    new Handler(selector,c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
    }


    final class Handler implements Runnable{
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>() ,Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        static final int PROCESSING = 3;
        final SocketChannel socket;
        final SelectionKey sk;
        ByteBuffer input = ByteBuffer.allocate(10*1024);
        ByteBuffer ouput = ByteBuffer.allocate(10*1024);
        static final int READING = 0,SENDING = 1;
        int state = READING;

        public Handler(Selector sel, SocketChannel c) throws IOException {
            socket = c;
            c.configureBlocking(false);
            sk = socket.register(sel,0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
        }

        boolean inputIsComplete(){
            return true;
        }

        boolean outputIsComplete(){
            return true;
        }

        void process(){
            System.out.println("---------");
        }

        @Override
        public void run() {
            try {
                if(state == READING) read();
                else if(state == SENDING) send();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

        synchronized void read() throws IOException {
            socket.read(input);
            if(inputIsComplete()){
                state = PROCESSING;
                pool.execute(new Processer());
            }
        }

        synchronized void ProcessAndHandOff(){
            process();
            state = SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);
        }

        class Processer implements Runnable{
            @Override
            public void run() {
                ProcessAndHandOff();
            }
        }

        void send() throws IOException {
            socket.write(ouput);
            if(outputIsComplete()){
                sk.cancel();
            }
        }
    }
}
