package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.request.LoginRequestPacket;
import com.home.netty.im.protocol.response.LoginResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 9:02 2019/9/24
 * @modified by:
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler(){
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());
        loginResponsePacket.setUserName(packet.getUserName());
        loginResponsePacket.setPassword(packet.getPassword());
        loginResponsePacket.setNikeName(packet.getNikeName());
        if(valid(packet)){
            String userId = UUID.randomUUID().toString();
            SessionUtil.bindSession(new Session(userId,packet.getUserName(), packet.getPassword(), packet.getPassword()),ctx.channel());
            loginResponsePacket.setSuccess(Boolean.TRUE);
            loginResponsePacket.setReason("登录成功");
            loginResponsePacket.setUserId(userId);
            System.out.println("唯一标识：" + userId + " ，昵称:【" + packet.getNikeName() + "】 登录成功!");
        } else {
            loginResponsePacket.setSuccess(Boolean.FALSE);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println("昵称:【" + packet.getNikeName() + "】 登录失败!");
        }
        //写数据
        ctx.channel().writeAndFlush(loginResponsePacket);
}

    /**
     * 用户密码校验，目前还是默认谁都能登录
     * @param loginRequestPacket
     * @return
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {

        return Boolean.TRUE;
      /*  if ("wulj".equalsIgnoreCase(loginRequestPacket.getUsername()) && "111111".equalsIgnoreCase(loginRequestPacket.getPassword())){
            return true;
        }
        return false;*/
    }

}
