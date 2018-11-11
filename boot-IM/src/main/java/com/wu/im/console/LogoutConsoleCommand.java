package com.wu.im.console;

import com.wu.im.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入用户ID");
        String userId = scanner.nextLine();
        LogoutRequestPacket packet = new LogoutRequestPacket();
        packet.setUserId(userId);
        channel.writeAndFlush(packet);
    }
}
