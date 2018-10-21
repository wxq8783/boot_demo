package com.wu.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class IOClient {
    public static void main(String[] args) throws IOException {
        try {
            for(int i=0;i<10000;i++){
                final int j = i;
                System.out.println(j);
                new Thread(()->{
                    try {

                        Socket socket = new Socket("127.0.0.1",8889);
                        while(true){
                            socket.getOutputStream().write((String.valueOf(j)+"-----"+new Date()+":"+"HelloWord").getBytes());
                            Thread.sleep(10000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
