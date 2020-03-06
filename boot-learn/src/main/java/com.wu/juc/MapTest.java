package com.wu.juc;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        HashMap hashMap = new HashMap(10);
        for(int i = 0;i<50;i++){

            map.putIfAbsent(i,i);
            hashMap.put(i,i);
        };
    }
}
