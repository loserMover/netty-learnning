package com.home.netty.im.handler;

import com.home.netty.im.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEART_BEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }


    private void scheduleSendHeartBeat(ChannelHandlerContext ctx){
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()){
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEART_BEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
