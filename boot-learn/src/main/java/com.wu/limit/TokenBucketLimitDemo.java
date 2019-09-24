package com.wu.limit;

public class TokenBucketLimitDemo {
    //时间刻度
    private static long time = System.currentTimeMillis();

    private static int createTokenRate = 3;

    private static int size = 10;

    private static int tokens = 0;

    private static boolean grant(){
        long now = System.currentTimeMillis();
        //这段时间内需要产生的令牌数
        int in = (int) ((now - time)/50 * createTokenRate);
        tokens = Math.min(size,tokens+in);
        time = now;
        if(tokens > 0){
            --tokens;
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<500;i++){
            new Thread(()->{
                if(grant()){
                    System.out.println("执行业务逻辑"+Thread.currentThread().getName());
                }else{
                    System.out.println("限流操作"+Thread.currentThread().getName());
                }
            }).start();

        }

    }
}
