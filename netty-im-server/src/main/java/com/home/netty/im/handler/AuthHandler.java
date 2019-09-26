package com.home.netty.im.handler;

import com.home.netty.im.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 16:54 2019/9/26
 * @modified by:
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(LoginUtil.hasLogin(ctx.channel())){
            //一次连接上后，登录成功，后续久不需要校验，可以动态删除该ChannelHandler
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            //没有登录，则关闭连接
            ctx.channel().close();
        }
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())){
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler -> channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler -> channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler -> channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler -> channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler -> channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("AuthHandler -> handlerAdded");
        super.handlerAdded(ctx);
    }
}
