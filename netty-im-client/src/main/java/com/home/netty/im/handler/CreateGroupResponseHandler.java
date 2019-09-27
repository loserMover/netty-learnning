package com.home.netty.im.handler;

import com.home.netty.im.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 16:46 2019/9/27
 * @modified by:
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()){
            System.out.print("群组创建成功，群组ID：【" + msg.getGroupId() + "】，");
            System.out.println("群成员：" + msg.getUserNameList());
        } else {
            System.out.println("群组创建失败");
        }
    }
}
