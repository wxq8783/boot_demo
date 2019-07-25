package com.wu.hsp.datastructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showArr();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getNum();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.heard();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
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

    public boolean isFull(){
        if(maxSize == front+1){
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        if(front == rear){
            return true;
        }
        return false;
    }


    public void add(int num){
        if(isFull()){
            System.out.println("---队列已经满了，不能在加");
            return;
        }
        arr[++front] = num;
    }

    public int getNum(){
        if(isEmpty()){
            System.out.println("---已经取完了，请重新添加后在取");
            return 0;
        }
         ++rear;
         int result = arr[rear];
         arr[rear] = 0;
         return result;
    }

    public int heard(){
        if(isEmpty()){
            System.out.println("---已经取完了，头节点不存在");
            return 0;
        }
        return arr[rear+1];
    }

    public void showArr(){
        if(isEmpty()){
            System.out.println("---队列为空");
            return ;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
}
