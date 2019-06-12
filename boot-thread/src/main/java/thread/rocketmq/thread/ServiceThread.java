package thread.rocketmq.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ServiceThread implements Runnable{

    protected volatile boolean stopped = false;
    protected volatile AtomicBoolean hasNotified = new AtomicBoolean(false);

    protected final CountDownLatch2 waitPoint = new CountDownLatch2(1);

    private Thread thread;

    public ServiceThread(){

    }


    public void start(){
        stopped = false;
        thread = new Thread(this,getThreadName());
        thread.setDaemon(false);
        thread.start();
    }

    public abstract String getThreadName();

    public boolean isStopped(){
        return stopped;
    }

    public void setStopped(boolean stopped){
        this.stopped = stopped;
    }


    public void weakUp(){
        if(hasNotified.compareAndSet(false,true)){
            waitPoint.countDown();
        }
    }

    public void waitForRunning(long interval){
        if(hasNotified.compareAndSet(true,false)){
            //唤醒时 需要的操作
            this.onWaitEnd();
            return;
        }

        waitPoint.reset();
        try {
            //等待时间
            waitPoint.await(interval,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            hasNotified.set(false);
            this.onWaitEnd();
        }

    }

    protected void onWaitEnd(){

    }

    public void action(){
        System.out.printf("当前线程[%s] 正在执行。。。,时间:[%s] \n",Thread.currentThread().getName(),System.currentTimeMillis());
    }
}
