package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.request.ListGroupMembersRequestPacket;
import com.home.netty.im.protocol.response.ListGroupMembersResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 19:29 2019/9/27
 * @modified by:
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket msg) throws Exception {
        // 1. 获取群的 ChannelGroup
        String groupId = msg.getGroupId();
        ChannelGroup channels = SessionUtil.getChannelGroup(groupId);
        // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
        List<Session> sessions = new ArrayList<>();
        for (Channel channel : channels){
            sessions.add(SessionUtil.getSession(channel));
        }
        // 3. 构建获取成员列表响应写回到客户端
        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setGroupId(groupId);
        listGroupMembersResponsePacket.setSessions(sessions);
        ctx.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
