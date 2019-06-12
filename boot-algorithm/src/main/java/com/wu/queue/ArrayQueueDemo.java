package com.wu.queue;

public class ArrayQueueDemo {
    public static void main(String[] args) {

    }
}

class ArrayQueue{

    private int maxSize;

    private int front;//队列的头

    private int rear;//队列的尾

    private int[] arr;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }




}
