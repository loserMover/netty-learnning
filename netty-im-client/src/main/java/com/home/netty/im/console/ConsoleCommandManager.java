package com.home.netty.im.console;

import com.home.netty.im.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 10:18 2019/9/27
 * @modified by:
 */
public class ConsoleCommandManager {

    private Map<String, ConsoleCommand> consoleCommandMap;




    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();

        //指令类型
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
   //     consoleCommandMap.put("sendToGroup", new CreateGroupConsoleCommand());
    }

    public void exec(Scanner scanner, Channel channel){
        //没有登录
        if (!SessionUtil.hasLogin(channel)){
            ConsoleCommand consoleCommand = new LoginConsoleCommand();
            consoleCommand.exec(scanner, channel);
            return ;
        }
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if(null != consoleCommand){
          consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别["+command+"]指令，请重新输入！");
        }

    }




}
