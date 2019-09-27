package com.home.netty.im.console;

import com.home.netty.im.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 13:46 2019/9/27
 * @modified by:
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入用户名：");
        //获取用户名
        String userName = scanner.nextLine();

        System.out.print("请输入用户密码：");
        //获取用户名
        String password = scanner.nextLine();

        System.out.print("请输入用户昵称：");
        //获取用户名
        String nikeName = scanner.nextLine();

        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserName(userName);
        packet.setPassword(password);
        packet.setNikeName(nikeName);
        channel.writeAndFlush(packet);
        //发送登录，模拟登录过程
        waitForLoginResponse();
    }

    private void waitForLoginResponse(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
