package com.wu.hsp.datastructure.stack;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.list();
        stack.pop();
        stack.pop();
        stack.list();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.list();
    }
}

class ArrayStack{

    public int top ;

    public int maxSize;

    public int[] arr;

    public ArrayStack(int maxSzie){
        this.top = -1;
        this.maxSize = maxSzie;
        arr = new int[maxSzie];
    }

    public boolean isFull(){
        return top == maxSize-1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满");
            return;
        }
        top++;
        arr[top] = value;
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            System.out.println("栈已空");
            return 0;
        }
        int value = arr[top];
        top--;
        return value;
    }

    //遍历
    public void list(){
        System.out.println("--------------------------");
        for(int t = top;t >= 0 ;t--){
            System.out.println("栈的指针:"+t+"  值是:"+arr[t]);
        }
        System.out.println("---------------------------");
    }

}
