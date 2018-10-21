package com.wu.thread.lockinterface;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheLock {
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.WriteLock w = lock.writeLock();
    static ReentrantReadWriteLock.ReadLock r = lock.readLock();
    static Map<String,String> map = new HashMap<>();


    public static String get(String key){
        r.lock();
        try {
            return map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            r.unlock();
        }
    }

    public static void set(String key,String value){
        w.lock();
        try {
            map.put(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }
    }


    public static void clear(){
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
