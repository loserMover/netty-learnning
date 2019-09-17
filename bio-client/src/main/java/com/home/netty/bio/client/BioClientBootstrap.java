package com.home.netty.bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 17:30 2019/9/16
 * @modified by:
 */
public class BioClientBootstrap {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", 1000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("send data for client, data: hello bio");
            String response = in.readLine();
            System.out.println("response for data: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in){
                    in.close();
                }
                if (null != out){
                    out.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
