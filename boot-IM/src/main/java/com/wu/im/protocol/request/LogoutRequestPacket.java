package com.wu.im.protocol.request;

import com.wu.im.protocol.Packet;
import lombok.Data;

import static com.wu.im.protocol.Command.LOGOUT_REQUEST;

@Data
public class LogoutRequestPacket extends Packet {

    private String userId;

    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
