package com.wu.callback;

public class CallBackTest {

    public static void main(String[] args) {
        Student student = new Student();
        Teacher teacher = new Teacher(student);
        teacher.askQuest();
    }
}
