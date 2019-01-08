package com.wu.other;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {

    private static final ThreadLocalUtil INSTANCE = new ThreadLocalUtil();

    private ThreadLocalUtil(){

    }

    public static ThreadLocalUtil getInstance(){
        return INSTANCE;
    }

    private static final ThreadLocal<Map<String,String>> MAP_THREAD_LOCAL = ThreadLocal.withInitial(() -> new HashMap<>());//new ThreadLocal<>();

    public String get(String key){
        return MAP_THREAD_LOCAL.get().get(key);
    }

    public void set(String key , String value){
        MAP_THREAD_LOCAL.get().put(key,value);
    }

    public void clear(){
        MAP_THREAD_LOCAL.get().clear();
        MAP_THREAD_LOCAL.remove();
    }
}
