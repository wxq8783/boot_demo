package com.wu.im.protocol.request;

import com.wu.im.protocol.Packet;
import lombok.Data;

import static com.wu.im.protocol.Command.QUIT_GROUP_REQUEST;

@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
