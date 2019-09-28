package com.home.netty.im.client;

import com.home.netty.im.codec.PacketDecoder;
import com.home.netty.im.codec.PacketEncoder;
import com.home.netty.im.codec.Spliter;
import com.home.netty.im.console.ConsoleCommand;
import com.home.netty.im.console.ConsoleCommandManager;
import com.home.netty.im.handler.*;
import com.home.netty.im.protocol.request.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 13:49 2019/9/20
 * @modified by:
 */
public class ImClientBootstrap {

    private static final int MAX_RETRY = 5;

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupNoticeResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new QuitGroupNoticeResponseHandler());
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        ch.pipeline().addLast(new GroupMessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
                        ch.pipeline().addLast(new HeartBeatResponseHandler());
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    /**
     * 连接服务，连接失败则重试
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry){
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("连接成功");
                //启动控制台线程
                startConsoleThread(((ChannelFuture)future).channel());
            } else if (MAX_RETRY == retry){
                System.err.println("连接失败到达最大次数，放弃连接");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.err.println("连接失败，第" + retry + "次重连....");
                bootstrap.config().group().schedule(() -> {
                    connect(bootstrap, host, port , retry - 1);
                }, delay, TimeUnit.SECONDS);
            }
        });
    }

    /**
     * 启动控制台线程
     * @param channel
     */
    private static void startConsoleThread(Channel channel){
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                consoleCommandManager.exec(scanner, channel);
            }
        }).start();
    }
}
