package com.wu.netty.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                doStart();
            }
        }).start();
    }

    public void doStart(){
        try {
            InputStream inputStream = socket.getInputStream();
            while (true){
                byte[] buf = new byte[1024];
                int len ;
                while ((len = inputStream.read(buf)) != -1){
                    String message = new String(buf,0,len);
                    System.out.println("客户端传来的数据："+message);
                    socket.getOutputStream().write(buf);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
