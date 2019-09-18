package com.home.netty.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioSelectorServerBootstrap {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            //开启监听端口9090
            serverSocketChannel.bind(new InetSocketAddress(9090));
            //设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            //注册channel，并且指定感兴趣的事件是Accept
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
            //初始化读写缓冲区
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put("received from client : ".getBytes("UTF-8"));
            writeBuffer.flip();
            while (true){
                int ready = selector.select();
                Iterator<SelectionKey> iterable = selector.selectedKeys().iterator();
                while (iterable.hasNext()){
                    SelectionKey key = iterable.next();
                    if (key.isAcceptable()){
                        //创建新的连接，并且把连接注册到selector上，而且声明这个channel只对读操作感兴趣
                        System.out.println("-------------");
                        SocketChannel channel = serverSocketChannel.accept();
                        channel.configureBlocking(false);
                        channel.register(selector,SelectionKey.OP_READ);
                    }

                    if (key.isConnectable()){
                        System.out.println("===========");
                    }

                    if (key.isReadable()){
                        SocketChannel channel = (SocketChannel)key.channel();
                        readBuffer.clear();
                        channel.read(readBuffer);
                        readBuffer.flip();
                        System.out.println("received : " + new String(readBuffer.array(),"UTF-8"));
                        key.interestOps(SelectionKey.OP_WRITE);


                    }

                    if (key.isWritable()){
                        writeBuffer.rewind();
                        SocketChannel channel = (SocketChannel)key.channel();
                        channel.write(writeBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    iterable.remove();
                }

            }












        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != serverSocketChannel){
                    serverSocketChannel.close();
                }
                if (null != selector){
                    selector.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
