package com.wu.thread.cpt2.cpt2_1.cpt2_1_3;

public class PublicVar21 {
    public String username = "A";
    public String password = "AA";
    synchronized public void setValue(String username , String password){
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name="+Thread.currentThread().getName() + " username="+username+" password="+password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //public void getValue(){
    synchronized public void getValue(){
        System.out.println("getValue method thread name="+Thread.currentThread().getName() + " username="+username+" password="+password);
    }
}
