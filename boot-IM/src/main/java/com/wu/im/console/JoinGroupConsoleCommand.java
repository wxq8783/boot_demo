package com.wu.im.console;

import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId,加入群聊：");
        String groupId = scanner.nextLine();


    }
}
