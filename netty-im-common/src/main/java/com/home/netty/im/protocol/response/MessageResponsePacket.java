package com.home.netty.im.protocol.response;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.command.Command;
import lombok.Data;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 12:44 2019/9/23
 * @modified by:
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String fromNikeName;

    /**
     * 消息响应内容
     */
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
