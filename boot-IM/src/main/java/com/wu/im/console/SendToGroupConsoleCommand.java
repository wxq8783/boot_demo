package com.wu.im.console;

import com.wu.im.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入群号和要发送的信息");
        String groupId = scanner.nextLine();
        String msg = scanner.nextLine();
        GroupMessageRequestPacket packet = new GroupMessageRequestPacket();
        packet.setGroupId(groupId);
        packet.setMessage(msg);
        channel.writeAndFlush(packet);
    }
}
