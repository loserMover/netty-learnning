package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.request.QuitGroupRequestPacket;
import com.home.netty.im.protocol.response.QuitGroupNoticeResponsePacket;
import com.home.netty.im.protocol.response.QuitGroupResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 19:17 2019/9/27
 * @modified by:
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = msg.getGroupId();
        ChannelGroup channels = SessionUtil.getChannelGroup(groupId);
        Session session = SessionUtil.getSession(ctx.channel());
        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(groupId);
        //判断群组是否存在
        if (null != channels) {
            channels.remove(ctx.channel());
            //通知群其他成员，告诉XX人退出群聊
            QuitGroupNoticeResponsePacket quitGroupNoticeResponsePacket = new QuitGroupNoticeResponsePacket();
            quitGroupNoticeResponsePacket.setJoinUserId(session.getUserId());
            quitGroupNoticeResponsePacket.setJoinUserName(session.getUserName());
            quitGroupNoticeResponsePacket.setJoinNikeName(session.getNikeName());
            channels.writeAndFlush(quitGroupNoticeResponsePacket);
            System.out.println(session.getUserId() + " -> 成功退出群组【" + groupId + "】");
            quitGroupResponsePacket.setSuccess(Boolean.TRUE);
            quitGroupResponsePacket.setReason("退出成功");
        } else {
            System.out.println(session.getUserId() + " -> 不存在群组【" + groupId + "】，退出失败");
            quitGroupResponsePacket.setSuccess(Boolean.FALSE);
            quitGroupResponsePacket.setReason("退出失败");
        }
        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
