package com.home.netty.im.console;

import com.home.netty.im.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 14:08 2019/9/27
 * @modified by:
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLIT = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【创建群聊】，请输入userId列表，userId之间使用英文逗号隔开：");
        String userIds = scanner.next();
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLIT)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
