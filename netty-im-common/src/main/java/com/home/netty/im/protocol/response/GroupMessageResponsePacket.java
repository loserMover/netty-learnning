package com.home.netty.im.protocol.response;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.command.Command;
import lombok.Data;

@Data
public class GroupMessageResponsePacket extends Packet {

    private String groupId;

    private String message;

    private String fromUserName;

    private String fromUserId;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
