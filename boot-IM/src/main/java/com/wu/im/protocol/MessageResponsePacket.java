package com.wu.im.protocol;

import static com.wu.im.protocol.Command.MESSAGE_RESPONSE;

public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
