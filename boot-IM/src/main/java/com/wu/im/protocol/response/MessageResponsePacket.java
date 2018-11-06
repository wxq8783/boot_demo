package com.wu.im.protocol.response;

import com.wu.im.protocol.Packet;
import lombok.Data;

import static com.wu.im.protocol.Command.MESSAGE_RESPONSE;
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
