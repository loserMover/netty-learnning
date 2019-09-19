package com.home.netty.nio.server;

import com.home.netty.nio.handler.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 15:47 2019/9/18
 * @modified by:
 */
public class NioNettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boos,worker)
                .channel(NioServerSocketChannel.class)
                .attr(AttributeKey.newInstance("serverName"),"NioNettyServer")
                .childAttr(AttributeKey.newInstance("clientKey"),"clientKey")
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        System.out.println("服务启动中。。。。");
                        System.out.println(ch.attr(AttributeKey.valueOf("serverName")).get());
                    }
                })
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println(ch.attr(AttributeKey.valueOf("clientKey")).get());
 //                       ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//                                System.out.println(ctx.channel().attr(AttributeKey.valueOf("clientKey")).get());
//                                System.out.println("received data : " + msg);
//                            }
//                        });
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });
        bind(serverBootstrap,8000);
               // .bind(8000);

    }

    /**
     * 重试绑定端口
     * @param serverBootstrap
     * @param port
     */
    public static void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(port).addListener(future -> {
           if(future.isSuccess()){
               System.out.println("端口["+port+"]绑定成功");
           } else {
               System.out.println("端口["+port+"]绑定失败");
                bind(serverBootstrap,port + 1);
           }
        });
    }
}
