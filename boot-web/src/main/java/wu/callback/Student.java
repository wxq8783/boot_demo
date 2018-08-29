package com.wu.callback;

public class Student {

    public void returnAnswer(CallBack callBack , String question){
        System.out.println("Teacher的问题是:"+question);

        String answer = question+"  答案是&&&&";

        callBack.callBack(answer);
    }
}
