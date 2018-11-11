package com.wu.im.protocol.response;

import com.wu.im.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.wu.im.protocol.Command.JOIN_GROUP_RESPONSE;

@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private List<String> userInfoList;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
