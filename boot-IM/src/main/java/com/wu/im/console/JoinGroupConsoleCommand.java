package com.wu.im.console;

import com.wu.im.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId,加入群聊：");
        String groupId = scanner.nextLine();

        JoinGroupRequestPacket packet = new JoinGroupRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}
