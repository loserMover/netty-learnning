package com.home.netty.im.protocol.response;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.command.Command;
import lombok.Data;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 18:36 2019/9/27
 * @modified by:
 */
@Data
public class QuitGroupNoticeResponsePacket extends Packet {

    private String joinUserId;

    private String joinUserName;

    private String joinNikeName;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_NOTICE_RESPONSE;
    }
}
