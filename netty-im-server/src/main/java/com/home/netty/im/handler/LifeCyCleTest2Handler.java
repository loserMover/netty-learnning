package com.home.netty.im.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 11:33 2019/9/26
 * @modified by:
 */
@ChannelHandler.Sharable
public class LifeCyCleTest2Handler extends ChannelInboundHandlerAdapter {

    public static final LifeCyCleTest2Handler INSTANCE = new LifeCyCleTest2Handler();

    protected LifeCyCleTest2Handler(){
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> channelRead");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> userEventTriggered");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    protected void ensureNotSharable() {
        System.out.println("LifeCyCleTest2Handler -> ensureNotSharable");
        super.ensureNotSharable();
    }

    @Override
    public boolean isSharable() {
        System.out.println("LifeCyCleTest2Handler -> isSharable");
        return super.isSharable();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeCyCleTest2Handler -> handlerRemoved");
        super.handlerRemoved(ctx);
    }
}
