package com.wu.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    @Override
    public void run() {
        try{
            ServerSocket ss = new ServerSocket(8889);
            while(!Thread.interrupted()){
                new Thread(new Handler(ss.accept())).start();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static class Handler implements Runnable{

        final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[10*1024];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }

        private byte[] process(byte[] input){
            return "---->返回测试数据".getBytes();
        }
    }
}
