package com.wu.im.protocol;

import lombok.Data;

import java.util.List;

import static com.wu.im.protocol.Command.CREATE_GROUP_RESPONSE;

@Data
public class CreateGroupResponsePacket extends Packet {

    private List<String> userNameList;

    private boolean success;

    private String groupId;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
