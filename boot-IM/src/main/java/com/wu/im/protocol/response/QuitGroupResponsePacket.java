package com.wu.im.protocol.response;

import com.wu.im.protocol.Packet;
import lombok.Data;

import static com.wu.im.protocol.Command.QUIT_GROUP_RESPONSE;

@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
