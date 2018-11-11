package com.wu.im.protocol.request;

import com.wu.im.protocol.Packet;
import lombok.Data;

import static com.wu.im.protocol.Command.JOIN_GROUP_REQUEST;

@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
