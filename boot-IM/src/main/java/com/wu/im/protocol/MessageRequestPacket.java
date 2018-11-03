package com.wu.im.protocol;

import lombok.Data;

import static com.wu.im.protocol.Command.MESSAGE_REQUEST;
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
