package com.wu.netty.view;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final int SLEEP_TIME = 5000;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST,PORT);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("客户端启动成功");
                    while (true){
                        String msg = "hello word :"+UUID.randomUUID();
                        System.out.println("客户端要发送到数据："+msg);
                        try {
                            socket.getOutputStream().write(msg.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
