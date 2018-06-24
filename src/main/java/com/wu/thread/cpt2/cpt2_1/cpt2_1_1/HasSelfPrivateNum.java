package com.wu.thread.cpt2.cpt2_1.cpt2_1_1;

public class HasSelfPrivateNum {
    private int num = 0; // 对象中的实例变量 非线程安全
    public void addI(String userName){
    //synchronized public void addI(String userName){//加锁
        try{
            //int num = 0; // 方法内 线程安全
            if(userName.equals("a")){
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            }else{
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(userName+" num="+num);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
