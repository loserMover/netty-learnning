package com.home.netty.im.handler;

import com.home.netty.im.protocol.PacketCodec;
import com.home.netty.im.protocol.request.LoginRequestPacket;
import com.home.netty.im.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 9:02 2019/9/24
 * @modified by:
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());
        if(valid(packet)){
            loginResponsePacket.setSuccess(Boolean.TRUE);
            loginResponsePacket.setReason("登录成功");
            System.out.println(new Date() + ": 登录成功!");
        } else {
            loginResponsePacket.setSuccess(Boolean.FALSE);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() + ": 登录失败!");
        }
        //写数据
        ctx.channel().writeAndFlush(loginResponsePacket);
    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if ("wulj".equalsIgnoreCase(loginRequestPacket.getUsername()) && "111111".equalsIgnoreCase(loginRequestPacket.getPassword())){
            return true;
        }
        return false;
    }

}
