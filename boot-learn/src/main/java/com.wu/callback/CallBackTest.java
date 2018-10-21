package com.wu.callback;

import java.util.concurrent.ConcurrentHashMap;

public class CallBackTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        for(int i=0 ;i < 1000;i++){
            System.out.println(i);
            map.put("num"+i,"--"+i);
        }

        String ii = map.get("num"+43);
        System.out.println("----------------"+ii);
    }
}
