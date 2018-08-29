package com.wu.thread.cpt3.cpt3_5;

import java.io.IOException;
import java.io.PipedInputStream;

public class ReadData {
    public void readMethod(PipedInputStream inputStream){
        try {
            System.out.println("read:");
            byte[] byteArr = new byte[20];
            int readLength = inputStream.read(byteArr);
            while (readLength != -1){
                String newData = new String(byteArr,0,readLength);
                System.out.println(newData);
                readLength = inputStream.read(byteArr);
            }
            System.out.println();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
