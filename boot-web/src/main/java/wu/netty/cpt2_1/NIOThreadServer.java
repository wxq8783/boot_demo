package com.wu.netty.cpt2_1;

public class NIOThreadServer {
    public static void main(String[] args) {
        int port = 8880;
        NIOThreadServerHandler handler = new NIOThreadServerHandler(port);
        new Thread(handler).start();
    }
}
