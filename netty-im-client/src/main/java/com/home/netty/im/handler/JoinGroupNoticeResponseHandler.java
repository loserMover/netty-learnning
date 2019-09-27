package com.home.netty.im.handler;

import com.home.netty.im.protocol.response.JoinGroupNoticeResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 19:51 2019/9/27
 * @modified by:
 */
public class JoinGroupNoticeResponseHandler extends SimpleChannelInboundHandler<JoinGroupNoticeResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupNoticeResponsePacket msg) throws Exception {
        System.out.println("【" + msg + "】，加入群聊");
    }
}
