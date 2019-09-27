package com.home.netty.im.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 10:16 2019/9/27
 * @modified by:
 */
public interface ConsoleCommand {

    /**
     * 命令执行
     * @param scanner
     * @param channel
     */
    void exec(Scanner scanner, Channel channel);

}
