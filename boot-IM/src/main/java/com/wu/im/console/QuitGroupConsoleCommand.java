package com.wu.im.console;

import com.wu.im.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId,退出群聊");
        String groupId = scanner.nextLine();
        QuitGroupRequestPacket packet = new QuitGroupRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}
