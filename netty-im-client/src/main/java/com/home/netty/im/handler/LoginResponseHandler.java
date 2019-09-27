package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.request.LoginRequestPacket;
import com.home.netty.im.protocol.response.LoginResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 9:29 2019/9/24
 * @modified by:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket packet) throws Exception {
        if (packet.isSuccess()){
            System.out.println("登录成功");
           SessionUtil.bindSession(new Session(packet.getUserId(),packet.getUserName(),packet.getPassword(),packet.getNikeName()),channelHandlerContext.channel());
        } else {
            System.out.println("登录失败，失败的原因：" + packet.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("接被关闭");
        super.channelInactive(ctx);
    }
}
