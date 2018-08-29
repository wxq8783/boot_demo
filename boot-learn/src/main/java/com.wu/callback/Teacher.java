package com.wu.callback;

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
