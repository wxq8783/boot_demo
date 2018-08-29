package com.wu.thread.cpt3.cpt3_5;

import java.io.PipedOutputStream;

public class WriteThead extends Thread {
    private WriteData writeData;
    private PipedOutputStream outputStream;

    public WriteThead(WriteData writeData, PipedOutputStream outputStream) {
        this.writeData = writeData;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        writeData.writeMethod(outputStream);
    }
}
