package com.home.netty.im.protocol.request;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.command.Command;
import lombok.Data;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 11:01 2019/9/27
 * @modified by:
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
