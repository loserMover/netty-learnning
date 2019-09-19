package com.home.netty.nio.client;

import com.home.netty.nio.handler.FirstServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 16:02 2019/9/18
 * @modified by:
 */
public class NioNettyClient {
    private static final int MAX_RETRY = 6;
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("nettyClient"),"NioNettyClient")
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        System.out.println(ch.attr(AttributeKey.valueOf("nettyClient")).get());
                  //      ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });

        connect(bootstrap,"127.0.0.1",8000,MAX_RETRY);
//        while (true){
//            channel.writeAndFlush(LocalTime.now().toString()+" : hello world !");
//            Thread.sleep(5000);
//        }

    }

    public static Channel connect(Bootstrap bootstrap, String host, int port, int retry){
        ChannelFuture channelFuture = bootstrap.connect(host,port);
        channelFuture.addListener(future -> {
            if(future.isSuccess()){
                System.out.println("连接成功");
            } else if (0 == retry){
                System.err.println("重试次数已经用完，放弃连接");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第"+order+"次重连......");
                bootstrap.config().group().schedule(()->{
                    connect(bootstrap, host, port, retry - 1);
                },delay, TimeUnit.SECONDS);
            }
        });
        return channelFuture.channel();
    }
}
