package com.wu.thread.cpt3.cpt3_1;

public class NoSynchnorizedException {
    /**
     * 没有synchronized，该代码会报错
     * IllegalMonitorStateException
     * @param args
     */
    public static void main(String[] args) {
        try {
            String newString = new String(" ");
            newString.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
