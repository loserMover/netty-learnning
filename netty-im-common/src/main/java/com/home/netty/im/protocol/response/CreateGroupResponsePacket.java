package com.home.netty.im.protocol.response;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 15:42 2019/9/27
 * @modified by:
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String reason;

    private List<String> userNameList;

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
