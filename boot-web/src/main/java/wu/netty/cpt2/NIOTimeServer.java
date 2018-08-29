package com.wu.netty.cpt2;

public class NIOTimeServer {
    public static void main(String[] args) {
        int port = 8090;
        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer," NIO-TimeServer-0001").start();
    }
}
