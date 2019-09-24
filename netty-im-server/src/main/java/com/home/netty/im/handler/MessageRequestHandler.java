package com.home.netty.im.handler;

import com.home.netty.im.protocol.PacketCodec;
import com.home.netty.im.protocol.request.MessageRequestPacket;
import com.home.netty.im.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 9:22 2019/9/24
 * @modified by:
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        //处理消息
        System.out.println(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());
        //响应请求
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageRequestPacket.setVersion(messageRequestPacket.getVersion());
        messageResponsePacket.setMessage("服务端回复：【" + messageRequestPacket.getMessage() + "】");
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
