package com.home.netty.im.handler;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.PacketCodec;
import com.home.netty.im.protocol.request.LoginRequestPacket;
import com.home.netty.im.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 16:50 2019/9/20
 * @modified by:
 */
@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter{

    /**
     * 连接上后发送用户名密码进行登录
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
        //通信协议编码
        ByteBuf byteBuf = PacketCodec.INSTANCE.encode(loginRequestPacket);
        //写数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    /**
     * 读写业务逻辑
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        //通信协议解码
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket)packet;
            if (loginResponsePacket.isSuccess()){
                System.out.println("客户端登录成功");
            } else {
                System.out.println("客户端登录失败，失败的原因：" + loginResponsePacket.getReason());
            }
        }
    }
}
