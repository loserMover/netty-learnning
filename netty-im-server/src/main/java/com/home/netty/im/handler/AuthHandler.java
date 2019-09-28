package com.home.netty.im.handler;

import com.home.netty.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 16:54 2019/9/26
 * @modified by:
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    protected AuthHandler(){

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(SessionUtil.hasLogin(ctx.channel())){
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
        if (SessionUtil.hasLogin(ctx.channel())){
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
