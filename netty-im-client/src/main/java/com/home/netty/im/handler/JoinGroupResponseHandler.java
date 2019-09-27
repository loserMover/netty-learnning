package com.home.netty.im.handler;

import com.home.netty.im.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 19:16 2019/9/27
 * @modified by:
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()){
            System.out.println("成功加入群组【" + msg.getGroupId() + "】");
        } else {
            System.out.println("群组【" + msg.getGroupId() + "】，加入失败");
        }
    }
}
