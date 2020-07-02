package com.arh.springbootdemo.nio;

import java.io.IOException;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/1
 **/
public class Client {

    public static final String DEFAULT_IP = "127.0.0.1";

    private static ClientHandle clientHandle;

    public static void start() {
        start(DEFAULT_IP, Server.DEFAULT_port);
    }

    public static synchronized void start(String ip, int port) {
        if (clientHandle != null) {
            clientHandle.stop();
        }
        clientHandle = new ClientHandle(ip, port);
        new Thread(clientHandle, "Thread-Client-Don").start();

    }

    public void sendMsg(String msg) throws IOException {
        clientHandle.sendMsg(msg);
    }
}
