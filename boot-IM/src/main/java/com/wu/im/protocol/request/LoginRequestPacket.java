package com.wu.im.protocol.request;

import com.wu.im.protocol.Packet;
import lombok.Data;

import static com.wu.im.protocol.Command.LOGIN_REQUEST;
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;



    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
