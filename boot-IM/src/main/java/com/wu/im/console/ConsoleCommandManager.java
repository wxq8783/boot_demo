package com.wu.im.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String,ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser",new SendToUserConsoleCommand());
        consoleCommandMap.put("logout",new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup",new CreateGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.nextLine();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(consoleCommand == null){
            System.out.println("无法识别【"+command+"】指令，请重新输入");
        }else{
            consoleCommand.exec(scanner,channel);
        }

    }
}
