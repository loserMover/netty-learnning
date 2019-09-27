package com.home.netty.im.handler;

import com.home.netty.im.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 19:27 2019/9/27
 * @modified by:
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()){
            System.out.println("成功退出群组【" + msg.getGroupId() + "】");
        } else {
            System.out.println("群组【" + msg.getGroupId() + "】，退出失败");
        }
    }
}
