package com.wu.thread.cpt3.cpt3_5;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class WRRun {
    /**
     * 通过PipedOutputStream和PipedInputStream进行数据传输
     * @param args
     */
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedOutputStream outputStream = new PipedOutputStream();
            PipedInputStream inputStream = new PipedInputStream();

            outputStream.connect(inputStream);

            ReadThread readThrad = new ReadThread(readData,inputStream);
            readThrad.start();

            Thread.sleep(3000);

            WriteThead writeThead = new WriteThead(writeData,outputStream);
            writeThead.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
