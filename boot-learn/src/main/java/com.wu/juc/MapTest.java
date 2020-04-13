package com.wu.juc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {

        String name = "wwww";
        int h = name.hashCode();
        System.out.println(h);
        int hh = h>>>16;
        System.out.println(hh);
        System.out.println(h^hh);
        ConcurrentHashMap map = new ConcurrentHashMap();
        HashMap hashMap = new HashMap(10);
        LinkedHashMap linkedHashMap = new LinkedHashMap(16,0.75f,true);
        for(int i = 0;i<50;i++){
            Object put = linkedHashMap.put(i, i);
            map.putIfAbsent(i,i);
            hashMap.put(i,i);
        };
    }
}
