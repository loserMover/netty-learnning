package com.home.netty.im.handler;

import com.home.netty.im.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 19:40 2019/9/27
 * @modified by:
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket msg) throws Exception {
        System.out.println("群ID【" + msg.getGroupId() + "】，种的成员包括：" + msg.getSessions());
    }
}
