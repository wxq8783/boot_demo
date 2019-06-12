package thread.rocketmq.thread;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class CountDownLatch2 {
    private Sync sync;

    public CountDownLatch2(int count){
        if(count < 0){
            throw new IllegalArgumentException("count < 0");
        }
        this.sync = new Sync(count);
    }


    public void  await() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean await(long timeout , TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(timeout));
    }

    public void countDown(){
        sync.releaseShared(1);
    }

    public long getCount(){
        return sync.getCount();
    }

    public void reset(){
        sync.reset();
    }


    private static final class Sync extends AbstractQueuedSynchronizer{
        private final int startCount;

        Sync(int count){
            this.startCount = count;
            setState(count);
        }

        int getCount(){
            return getState();
        }


        protected int tryAcquireShared(int acquire ){
            return (getState() == 0) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for(;;){
                int c = getState();
                if(c == 0){
                    return false;
                }
                int nextx = c -1 ;
                if(compareAndSetState(c,nextx)){
                    return nextx == 0;
                }
            }
        }

        public void reset(){
            setState(startCount);
        }

    }
}
