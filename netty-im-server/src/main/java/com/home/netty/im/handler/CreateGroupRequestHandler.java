package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.request.CreateGroupRequestPacket;
import com.home.netty.im.protocol.response.CreateGroupResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 16:33 2019/9/27
 * @modified by:
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    protected CreateGroupRequestHandler(){
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> list = msg.getUserIdList();
        //创建一个channel分组
        ChannelGroup channels = new DefaultChannelGroup(ctx.executor());
        List<String> userNameList = new ArrayList<>();
        //筛选出加入群聊的用户channel和userName
        for (String userId : list){
            Channel channel = SessionUtil.getChannel(userId);
            channels.add(channel);
            userNameList.add(SessionUtil.getSession(channel).getUserName());
        }
        //创建群聊响应
        String groupId = UUID.randomUUID().toString();
        SessionUtil.bindChannelGroup(groupId, channels);
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setReason("成功创建群聊");
        createGroupResponsePacket.setSuccess(Boolean.TRUE);
        createGroupResponsePacket.setUserNameList(userNameList);
        //通过channel分组给用户发送通知
        channels.writeAndFlush(createGroupResponsePacket);
        System.out.println("创建群聊成功，群组ID：【" + groupId + "】");
        System.out.println("群组成员：" + userNameList);
    }
}
