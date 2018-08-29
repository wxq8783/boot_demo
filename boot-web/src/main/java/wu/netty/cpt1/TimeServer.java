package com.wu.netty.cpt1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 天&赐&清 on 2018/7/10.
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8090;
        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            Socket socket = null;
            while(true){
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(server != null){
                System.out.println("the time server close");
                server.close();
                server = null;
            }
        }
    }
}
