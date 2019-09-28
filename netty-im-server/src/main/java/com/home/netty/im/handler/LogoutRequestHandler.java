package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.request.LoginRequestPacket;
import com.home.netty.im.protocol.request.LogoutRequestPacket;
import com.home.netty.im.protocol.response.LogoutResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 10:57 2019/9/27
 * @modified by:
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    protected LogoutRequestHandler(){
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket packet) throws Exception {
        Channel channel = ctx.channel();
        Session session = SessionUtil.getSession(channel);
        SessionUtil.unBindSession(channel);
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(Boolean.TRUE);
        logoutResponsePacket.setReason("成功退出！");
        System.out.println("昵称：【" + session.getNikeName() + "】成功下线");
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
