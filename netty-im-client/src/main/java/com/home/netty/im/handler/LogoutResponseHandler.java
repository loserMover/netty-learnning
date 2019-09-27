package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.response.LoginResponsePacket;
import com.home.netty.im.protocol.response.LogoutResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 11:18 2019/9/27
 * @modified by:
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        if (msg.isSuccess()){
            SessionUtil.unBindSession(ctx.channel());
            System.out.println(msg.getReason());
        } else {
            System.out.println("用户退出异常");
        }

    }
}
