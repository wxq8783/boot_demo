package com.wu.im.protocol.request;

import com.wu.im.protocol.Packet;
import lombok.Data;

import static com.wu.im.protocol.Command.GROUP_MESSAGE_REQUEST;

@Data
public class GroupMessageRequestPacket extends Packet {

    private String groupId;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
