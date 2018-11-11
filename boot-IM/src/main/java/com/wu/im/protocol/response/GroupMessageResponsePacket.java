package com.wu.im.protocol.response;

import com.wu.im.protocol.Packet;
import lombok.Data;

import static com.wu.im.protocol.Command.GROUP_MESSAGE_RESPONSE;

@Data
public class GroupMessageResponsePacket extends Packet {

    private boolean success;

    private String reason;

    private String message;

    private String userId;

    private String userName;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
