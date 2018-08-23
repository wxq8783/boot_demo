package com.wu.netty.view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {

        }
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                doStart();
            }
        }).start();
    }

    public void  doStart(){
        while (true){
            try {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
