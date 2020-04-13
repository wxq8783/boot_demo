package com.wu.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(1,2);
        atomicInteger.getAndIncrement();
    }
}
