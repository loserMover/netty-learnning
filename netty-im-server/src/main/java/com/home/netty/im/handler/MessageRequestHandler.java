package com.home.netty.im.handler;

import com.home.netty.im.domain.Session;
import com.home.netty.im.protocol.PacketCodec;
import com.home.netty.im.protocol.request.MessageRequestPacket;
import com.home.netty.im.protocol.response.MessageResponsePacket;
import com.home.netty.im.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
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
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        Channel channel = SessionUtil.getChannel(packet.getToUserId());
        if (null != channel && SessionUtil.hasLogin(channel)){
            //响应请求
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setVersion(packet.getVersion());
            messageResponsePacket.setFromUserId(session.getUserId());
            messageResponsePacket.setFromUserName(session.getUserName());
            messageResponsePacket.setFromNikeName(session.getNikeName());
            messageResponsePacket.setMessage(packet.getMessage());
            channel.writeAndFlush(messageResponsePacket);
        } else {
            //保存起来，等上线再发送
        }


    }
}
