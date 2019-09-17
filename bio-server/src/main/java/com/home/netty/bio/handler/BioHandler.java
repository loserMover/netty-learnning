package com.home.netty.bio.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;

/**
 * @author: wulj13232
 * @description: 实现业务处理
 * @date: Created in 16:32 2019/9/16
 * @modified by:
 */
public class BioHandler implements Runnable {

    private Socket socket;


    public BioHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
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
