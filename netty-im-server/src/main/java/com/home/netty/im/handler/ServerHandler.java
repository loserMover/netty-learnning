package com.home.netty.im.handler;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.PacketCodec;
import com.home.netty.im.protocol.request.LoginRequestPacket;
import com.home.netty.im.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 17:10 2019/9/20
 * @modified by:
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 服务接收读写逻辑
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        //通信协议解码
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket){
            //登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginRequestPacket.setVersion(packet.getVersion());
            if(valid(loginRequestPacket)){
                loginResponsePacket.setSuccess(Boolean.TRUE);
                loginResponsePacket.setReason("登录成功");
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setSuccess(Boolean.FALSE);
                loginResponsePacket.setReason("账号密码校验失败");
                System.out.println(new Date() + ": 登录失败!");
            }
            //同学协议编码
            ByteBuf responseBuf = PacketCodec.INSTANCE.encode(loginResponsePacket);
            //写数据
            ctx.channel().writeAndFlush(responseBuf);
        }







    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if ("wulj".equalsIgnoreCase(loginRequestPacket.getUsername()) && "111111".equalsIgnoreCase(loginRequestPacket.getPassword())){
            return true;
        }
        return false;
    }
}
