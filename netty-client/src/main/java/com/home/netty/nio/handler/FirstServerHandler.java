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
 * @date: Created in 16:33 2019/9/19
 * @modified by:
 */
@ChannelHandler.Sharable
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端已经成功连接到服务，向服务发送数据");
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes("你好，吴林俊！".getBytes("UTF-8"));
        ctx.channel().writeAndFlush(buf);
        buf.release();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        System.out.println(new Date() + ": 客户端接收到服务端发送的数据：" + buf.toString(Charset.forName("UTF-8")));
        buf.release();
    }
}
