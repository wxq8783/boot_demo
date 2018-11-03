package com.wu.im.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Packet implements Serializable {
    /**
     * 协议版本
     */
    private Byte version = 1;


    public abstract Byte getCommand();

}
