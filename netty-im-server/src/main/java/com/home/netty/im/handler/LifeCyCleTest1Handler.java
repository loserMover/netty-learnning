package com.home.netty.im.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 11:33 2019/9/26
 * @modified by:
 */
public class LifeCyCleTest1Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> channelRead");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> userEventTriggered");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    protected void ensureNotSharable() {
        System.out.println("LifeCyCleTest1Handler -> ensureNotSharable");
        super.ensureNotSharable();
    }

    @Override
    public boolean isSharable() {
        System.out.println("LifeCyCleTest1Handler -> isSharable");
        return super.isSharable();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest1Handler -> handlerRemoved");
        super.handlerRemoved(ctx);
    }
}
