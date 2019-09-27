package com.home.netty.im.protocol.request;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.command.Command;
import lombok.Data;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 18:19 2019/9/27
 * @modified by:
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
