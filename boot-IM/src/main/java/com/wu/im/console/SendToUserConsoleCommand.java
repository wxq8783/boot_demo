package com.wu.im.console;

import com.wu.im.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入发送人和输入发送消息");
        String toUserId = scanner.nextLine();
        String msg = scanner.nextLine();
        channel.writeAndFlush(new MessageRequestPacket(toUserId,msg));
    }
}
