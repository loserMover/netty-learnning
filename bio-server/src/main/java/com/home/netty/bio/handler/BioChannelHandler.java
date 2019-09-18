package com.home.netty.bio.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.time.LocalTime;

public class BioChannelHandler implements  Runnable {
    private SocketChannel channel;


    public BioChannelHandler(SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(channel.socket().getInputStream()));
            out = new PrintWriter(channel.socket().getOutputStream(), true);
            while (true) {
                String line = in.readLine();
                if (null == line) {
                    break;
                }
                System.out.println("server receive data: " + line);
                out.println(LocalTime.now().toString() + " : " + line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
