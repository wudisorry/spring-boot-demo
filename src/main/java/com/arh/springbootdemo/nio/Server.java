package com.arh.springbootdemo.nio;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/1
 **/
public class Server {

    public static final int DEFAULT_port = 8990;

    private static ServerHandle serverHandle;

    public static void start() {
        start(DEFAULT_port);
    }

    public static synchronized void start(int port) {
        if (serverHandle != null) {
            serverHandle.stop();
        }
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle, "Thread-Server-Don").start();

    }

    public static void main(String[] args) {
        start();
    }
}
