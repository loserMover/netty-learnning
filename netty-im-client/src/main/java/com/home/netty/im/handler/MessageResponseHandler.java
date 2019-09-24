package com.home.netty.im.handler;

import com.home.netty.im.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 9:34 2019/9/24
 * @modified by:
 */
@ChannelHandler.Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println(new Date() + ": 接收到服务端的消息：" + messageResponsePacket.getMessage());
    }
}
