package com.wu.thread.cpt3.cpt3_5;

import java.io.IOException;
import java.io.PipedOutputStream;

public class WriteData {
    public void writeMethod(PipedOutputStream outputStream){
        try {
            System.out.println("write:");
            for(int i=0;i<3000;i++){
                String outDate = ""+(i+1);
                outputStream.write(outDate.getBytes());
                System.out.println("----"+outDate);
            }
            System.out.println();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
