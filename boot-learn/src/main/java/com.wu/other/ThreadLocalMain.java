package com.wu.other;

public class ThreadLocalMain {

    public static void main(String[] args) {
        ThreadLocalUtil.getInstance().set("key1","value111");
        ThreadLocalUtil.getInstance().set("key2","value222");
        test1();
        test2();
        test3();
    }

    public static String test1(){
        String value = ThreadLocalUtil.getInstance().get("key1");
        System.out.println(value);
        String value2 = ThreadLocalUtil.getInstance().get("key2");
        System.out.println(value2);
        ThreadLocalUtil.getInstance().set("key2","2222222");
        return value;
    }

    public static String test2(){
        String value = ThreadLocalUtil.getInstance().get("key1");
        System.out.println(value);
        String value2 = ThreadLocalUtil.getInstance().get("key2");
        System.out.println(value2);
        ThreadLocalUtil.getInstance().clear();
        return "";
    }

    public static String test3(){
        System.out.println("---------------");
        String value = ThreadLocalUtil.getInstance().get("key1");
        System.out.println(value);
        String value2 = ThreadLocalUtil.getInstance().get("key2");
        System.out.println(value2);
        return "";
    }
}
