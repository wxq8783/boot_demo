package thread.concurrent;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueuedSynchronizerDemo {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(AbstractQueuedSynchronizerDemo::action);

        executorService.submit(AbstractQueuedSynchronizerDemo::action);

        executorService.awaitTermination(200,TimeUnit.SECONDS);
        executorService.shutdown();
    }

    private static void action(){
        System.out.printf("当前线程【%s】 正在等待你的输入\n",Thread.currentThread().getName());
        try {
            //利用 ReentrantLock作为AQS
            lock.lock();
            System.in.read();
            System.out.printf("当前线程【%s】执行结束\n",Thread.currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
