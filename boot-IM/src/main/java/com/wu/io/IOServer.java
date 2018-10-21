package com.wu.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try {
                    int len ;
                    byte[] data = new byte[1024];
                    InputStream inputStream = socket.getInputStream();
                    while ((len = inputStream.read(data)) != -1){
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(new String(data,0,len));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
