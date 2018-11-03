package com.wu.im.protocol;

import static com.wu.im.protocol.Command.MESSAGE_REQUEST;

public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
