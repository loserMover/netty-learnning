package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.request.GroupMessageRequestPacket;
import com.home.netty.im.protocol.response.GroupMessageResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    protected GroupMessageRequestHandler(){
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        //组装响应消息
        String groupId = msg.getGroupId();
        Session session = SessionUtil.getSession(ctx.channel());
        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromUserId(session.getUserId());
        groupMessageResponsePacket.setFromUserName(session.getUserName());
        groupMessageResponsePacket.setGroupId(groupId);
        groupMessageResponsePacket.setMessage(msg.getMessage());
        //回写响应消息
        ChannelGroup channels = SessionUtil.getChannelGroup(groupId);
        channels.writeAndFlush(groupMessageResponsePacket);
    }
}
