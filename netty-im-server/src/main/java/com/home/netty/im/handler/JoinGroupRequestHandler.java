package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.request.JoinGroupRequestPacket;
import com.home.netty.im.protocol.response.JoinGroupNoticeResponsePacket;
import com.home.netty.im.protocol.response.JoinGroupResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 19:02 2019/9/27
 * @modified by:
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = msg.getGroupId();
        ChannelGroup channels = SessionUtil.getChannelGroup(groupId);
        // 2. 构造加群响应发送给客户端
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setGroupId(groupId);
        Session session = SessionUtil.getSession(ctx.channel());
        //判断群组是否存在
        if (null != channels){
            //通知群其他成员，告诉XX人加入群聊
            JoinGroupNoticeResponsePacket joinGroupNoticeResponsePacket = new JoinGroupNoticeResponsePacket();
            joinGroupNoticeResponsePacket.setJoinUserId(session.getUserId());
            joinGroupNoticeResponsePacket.setJoinUserName(session.getUserName());
            joinGroupNoticeResponsePacket.setJoinNikeName(session.getNikeName());
            channels.writeAndFlush(joinGroupNoticeResponsePacket);
            channels.add(ctx.channel());
            System.out.println(session.getUserId() + " -> 成功加入群组【" + groupId + "】");
            joinGroupResponsePacket.setSuccess(Boolean.TRUE);
            joinGroupResponsePacket.setReason("加入成功");
        } else {
            System.out.println(session.getUserId() + " -> 不存在群组【" + groupId + "】，加入失败");
            joinGroupResponsePacket.setSuccess(Boolean.FALSE);
            joinGroupResponsePacket.setReason("加入失败");
        }
        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
