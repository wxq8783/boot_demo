package com.wu.im.console;

import com.wu.im.protocol.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITE = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【拉人群聊】输入userId列表，userId之间用,号隔开");
        String userIds = scanner.nextLine();

        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        packet.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITE)));
        channel.writeAndFlush(packet);
    }
}
