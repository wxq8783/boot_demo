package com.wu.callback;

/**
 * A调用B中的方法b，需要传入A自身引用，方法b执行完毕后，再利用传入的A的引用，调用A中的方法
 */
public class Teacher implements CallBack {

    private Student student;

    public Teacher(Student student) {
        this.student = student;
    }

    @Override
    public void callBack(String param) {
        System.out.println("Student的回答:"+param);
    }

    public void askQuest(){
        student.returnAnswer(this,"请问今天是几号？");
    }
}
