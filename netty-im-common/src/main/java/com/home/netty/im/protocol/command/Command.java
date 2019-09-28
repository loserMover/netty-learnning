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

    /**
     * 用户退出指令
     */
    Byte LOGOUT_REQUEST = 5;

    /**
     * 用户退出响应指令
     */
    Byte LOGOUT_RESPONSE = 6;

    /**
     * 创建群组指令
     */
    Byte CREATE_GROUP_REQUEST = 7;

    /**
     * 创建群组响应指令
     */
    Byte CREATE_GROUP_RESPONSE = 8;

    /**
     * 加入群聊请求指令
     */
    Byte JOIN_GROUP_REQUEST = 9;

    /**
     * 加入群聊响应指令
     */
    Byte JOIN_GROUP_RESPONSE = 10;

    /**
     * 加入群聊响应通知其他人指令
     */
    Byte JOIN_GROUP_NOTICE_RESPONSE = 11;

    /**
     * 退出群聊请求指令
     */
    Byte QUIT_GROUP_REQUEST = 12;

    /**
     * 退出群聊响应指令
     */
    Byte QUIT_GROUP_RESPONSE = 13;

    /**
     * 退出群聊响应通知其他人指令
     */
    Byte QUIT_GROUP_NOTICE_RESPONSE = 14;

    /**
     * 获取群组所有人请求指令
     */
    Byte LIST_GROUP_MEMBERS_REQUEST = 15;

    /**
     * 获取群组所有人响应质量
     */
    Byte LIST_GROUP_MEMBERS_RESPONSE = 16;

    /**
     * 群组消息请求
     */
    Byte GROUP_MESSAGE_REQUEST = 17;

    /**
     * 群组消息相应
     */
    Byte GROUP_MESSAGE_RESPONSE = 18;

    /**
     * 心跳请求
     */
    Byte HEART_REQUEST = 19;

    /**
     * 心跳响应
     */
    Byte HEART_RESPONSE = 20;
}
