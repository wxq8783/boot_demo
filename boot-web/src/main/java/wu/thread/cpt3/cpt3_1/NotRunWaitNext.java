package com.wu.thread.cpt3.cpt3_1;

public class NotRunWaitNext {
    /**
     * 证明 wait()后面的代码是不执行的
     *
     * syc 上面
     * syn 第一行
     * @param args
     */
    public static void main(String[] args) {
        try {
            String lock = new String();
            System.out.println("syc 上面");
            synchronized (lock){
                System.out.println("syn 第一行 ");
                lock.wait();
                System.out.println("wait() 下的代码");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
