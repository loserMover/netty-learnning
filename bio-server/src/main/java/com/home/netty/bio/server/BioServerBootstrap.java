package com.home.netty.bio.server;

import com.home.netty.bio.handler.BioChannelHandler;
import com.home.netty.bio.handler.BioHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 16:34 2019/9/16
 * @modified by:
 */
public class BioServerBootstrap {

    public static void main(String[] args) {

       Executor executor = Executors.newFixedThreadPool(5);
        ServerSocketChannel ssc = null;
        try {
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8080));
          //  ssc.bind(new InetSocketAddress(8080));
        //    ssc.configureBlocking(false);
            while (true){
                SocketChannel channel =  ssc.accept();
                executor.execute(new BioChannelHandler(channel));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ssc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




      /*  ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(1000));
            for (;;){
                Socket socket = serverSocket.accept();
                //1.提交线程处理
             //   new Thread(new BioHandler(socket)).start();

                //提交线程池处理
                executor.execute(new BioHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
            }
        }
*/

    }
}
