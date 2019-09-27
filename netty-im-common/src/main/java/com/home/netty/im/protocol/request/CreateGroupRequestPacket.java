package com.home.netty.im.protocol.request;

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
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
