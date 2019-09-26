package com.home.netty.im.handler;

import com.home.netty.im.protocol.PacketCodec;
import com.home.netty.im.protocol.request.LoginRequestPacket;
import com.home.netty.im.protocol.request.MessageRequestPacket;
import com.home.netty.im.protocol.response.LoginResponsePacket;
import com.home.netty.im.util.LoginUtil;
import io.netty.buffer.ByteBuf;
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
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket>{

    /**
     * 与服务端建立连接后，触发该事件，目前用于用户登录业务
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("客户端开始登录");
        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("wulj");
        loginRequestPacket.setPassword("111111");
        //写数据
        ctx.channel().writeAndFlush(loginRequestPacket);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()){
            System.out.println("客户端登录成功");
//            LoginUtil.markAsLogin(channelHandlerContext.channel());
        } else {
            System.out.println("客户端登录失败，失败的原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
        super.channelInactive(ctx);
    }
}
