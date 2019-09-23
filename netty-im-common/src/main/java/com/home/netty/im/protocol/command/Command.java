package com.home.netty.im.protocol.command;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 17:37 2019/9/20
 * @modified by:
 */
public interface Command {

    /**
     * 登录请求指令
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登录响应指令
     */
    Byte LOGIN_RESPONSE= 2;

    /**
     * 消息请求指令
     */
    Byte MESSAGE_REQUEST= 3;

    /**
     * 消息响应指令
     */
    Byte MESSAGE_RESPONSE = 4;

}
