package com.home.netty.nio.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 16:39 2019/9/19
 * @modified by:
 */
@ChannelHandler.Sharable
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        System.out.println(new Date() + ": 服务端读取到数据 -> " + buf.toString(Charset.forName("UTF-8")));

        System.out.println("服务端给客户端发送数据");
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("欢迎来到Netty的世界".getBytes("UTF-8"));
        ctx.channel().writeAndFlush(byteBuf);
        byteBuf.release();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive客户端连接上服务器的时，服务器给客户端发送数据");
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("channelActive欢迎来到Netty的坑".getBytes("UTF-8"));
        ctx.channel().writeAndFlush(byteBuf);
        byteBuf.release();
    }
}
