package com.wu.thread.lockinterface;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {
    public static class MyCall<String> implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println("---------call is start");
            Thread.sleep(10000);
            System.out.println("---------call is end");
            return "call finished";
        }
    }

    public static void main(String[] args) {
        FutureTask task = new FutureTask(new MyCall());
        new Thread(task).start();
        try {
            //task.cancel(false);
            System.out.println(task.isDone());
            String result = (String) task.get();
            System.out.println(task.isDone());
            System.out.println("------"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
