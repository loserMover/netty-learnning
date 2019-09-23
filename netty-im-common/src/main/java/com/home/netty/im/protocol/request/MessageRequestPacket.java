package com.home.netty.im.protocol.request;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.command.Command;
import lombok.Data;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 12:42 2019/9/23
 * @modified by:
 */
@Data
public class MessageRequestPacket extends Packet {

    /**
     * 消息请求内容
     */
    private String message;


    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
