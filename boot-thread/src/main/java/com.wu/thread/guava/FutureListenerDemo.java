package com.wu.thread.guava;

import com.google.common.util.concurrent.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.*;

public class FutureListenerDemo {
    // 创建线程池
    final static ExecutorService service = Executors.newCachedThreadPool();

    public static void main0(String[] args) throws InterruptedException, ExecutionException {
        Long t1 = System.currentTimeMillis();

        // 任务1
        Future<Boolean> booleanTask = service.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return true;
            }
        });

        while (true) {
            if (booleanTask.isDone() && !booleanTask.isCancelled()) {
                //模拟耗时
                Thread.sleep(500);
                Boolean result = booleanTask.get();
                System.err.println("BooleanTask: " + result);
                break;
            }
        }

        // 任务2
        Future<String> stringTask = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        });

        while (true) {
            if (stringTask.isDone() && !stringTask.isCancelled()) {
                String result = stringTask.get();
                System.err.println("StringTask: " + result);
                break;
            }
        }



        // 任务3
        Future<Integer> integerTask = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });

        while (true) {
            if (integerTask.isDone() && !integerTask.isCancelled()) {
                Integer result = integerTask.get();
                System.err.println("IntegerTask: " + result);
                break;
            }
        }

        // 执行时间
        System.err.println("time: " + (System.currentTimeMillis() - t1));
    }


    private final static ListeningExecutorService getService2 = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void main2(String[] args) {
        ListenableFuture<String> future22 = getService2.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new Exception("抛出异常现象");
                //return "我是测试的";
            }
        });

        Futures.addCallback(future22, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("------"+result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        });
    }


    // 创建线程池
    final static ListeningExecutorService service1 = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void main1(String[] args) {
        Long t1 = System.currentTimeMillis();
        // 任务1
        ListenableFuture<Boolean> booleanTask = service1.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return true;
            }
        });

        Futures.addCallback(booleanTask, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                System.err.println("BooleanTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        // 任务2
        ListenableFuture<String> stringTask = service1.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        });

        Futures.addCallback(stringTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.err.println("StringTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        // 任务3
        ListenableFuture<Integer> integerTask = service1.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });

        Futures.addCallback(integerTask, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("IntegerTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        // 执行时间
        System.err.println("time: " + (System.currentTimeMillis() - t1));
    }


}
