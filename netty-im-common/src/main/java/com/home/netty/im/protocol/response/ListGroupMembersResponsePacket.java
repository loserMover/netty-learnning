package com.home.netty.im.protocol.response;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 18:25 2019/9/27
 * @modified by:
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessions;


    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
