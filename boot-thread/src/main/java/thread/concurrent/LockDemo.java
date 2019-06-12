package thread.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public void test() throws InterruptedException {
        final Lock lock = new ReentrantLock();

        lock.lock();
        Thread t1 = new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" interrupted");
        },"child thread-1");
        t1.start();
        Thread.sleep(1000);

        t1.interrupt();

        Thread.sleep(1000000);
    }


    public void testInterruptible() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();

        Thread t2 = new Thread(()->{
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+" interrupted");
            }
        },"child thread - 2");

        t2.start();
        Thread.sleep(1000);

        t2.interrupt();


    }

    public static void main(String[] args) throws InterruptedException {
        new LockDemo().testInterruptible();
    }

}
