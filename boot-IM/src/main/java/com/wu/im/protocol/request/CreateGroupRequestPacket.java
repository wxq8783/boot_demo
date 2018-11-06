package com.wu.im.protocol.request;

import com.wu.im.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.wu.im.protocol.Command.CREATE_GROUP_REQUEST;
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
