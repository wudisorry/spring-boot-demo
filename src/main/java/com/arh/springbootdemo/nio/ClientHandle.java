package com.arh.springbootdemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/1
 **/
public class ClientHandle implements Runnable {

    private String ip;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean started;

    public ClientHandle(String ip, int port) {
        try {
            this.ip = ip;
            this.port = port;
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            doConnection();
        } catch (IOException e) {
            e.printStackTrace();
            started = false;
        }
        while (started) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    handle(selectionKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handle(SelectionKey selectionKey) throws IOException {
        if(selectionKey.isValid()){
            SocketChannel sc= (SocketChannel) selectionKey.channel();
            if(sc.isConnected()){
                if(sc.finishConnect());
                else System.exit(1);
            }
            if(selectionKey.isReadable()){

            }


        }
    }

    private void doConnection() throws IOException {

        socketChannel.connect(new InetSocketAddress(ip, port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

    }

    public void stop() {
        started = false;
    }
}
