package com.arh.springbootdemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
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
            System.exit(1);
        }

    }

    @Override
    public void run() {
        try {
            doConnection();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (started) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    try {
                        handle(selectionKey);
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (selectionKey != null) {
                            selectionKey.cancel();
                            if (selectionKey.channel() != null) {
                                selectionKey.channel().close();
                            }

                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                started = false;
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
        if (selectionKey.isValid()) {
            SocketChannel sc = (SocketChannel) selectionKey.channel();
            if (sc.isConnected()) {
                if (sc.finishConnect()) {
                    System.exit(1);
                }
            }
            if (selectionKey.isReadable()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int byteNumber = sc.read(byteBuffer);
                if (byteNumber > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String expression = new String(bytes, "UTF-8");
                    System.out.println("客户端收到消息：" + expression);
                } else if (byteNumber < 0) {
                    selectionKey.cancel();
                    sc.close();
                }

            }


        }
    }

    private void doWrite(SocketChannel socketChannel, String msg) throws IOException {
        byte[] bytes = msg.getBytes("UTF-8");
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        socketChannel.write(byteBuffer);
    }

    public void sendMsg(String msg) throws IOException {
        socketChannel.register(selector, SelectionKey.OP_READ);
        doWrite(socketChannel, msg);
    }

    private void doConnection() throws IOException {
        socketChannel.connect(new InetSocketAddress(ip, port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

    }

    public void stop() {
        started = false;
    }
}
