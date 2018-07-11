package com.wu.netty.cpt1;

/**
 * Created by 天&赐&清 on 2018/7/11.
 */
public class NIOTimeClient {
    public static void main(String[] args) {
        int port = 8090;
        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new Thread(new NIOTimeClientHandler("127.0.0.1",port),"timeClient-0001").start();
    }
}
