package com.home.netty.im.handler;

import com.home.netty.im.protocol.response.QuitGroupNoticeResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 19:49 2019/9/27
 * @modified by:
 */
public class QuitGroupNoticeResponseHandler extends SimpleChannelInboundHandler<QuitGroupNoticeResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupNoticeResponsePacket msg) throws Exception {
        System.out.println("【" + msg + "】，退出群聊");
    }
}
