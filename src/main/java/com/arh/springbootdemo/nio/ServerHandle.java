package com.arh.springbootdemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/1
 **/
public class ServerHandle implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean started;

    public static final int DEFAULT_BACKLOG = 1024;

    public ServerHandle(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞 nio必设
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), DEFAULT_BACKLOG);
//            serverSocketChannel.bind()
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            started = true;
            System.out.println("服务已经启动，端口：" + port);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {

        }

    }


    @Override
    public void run() {
        while (started) {
            try {
                //无参select()方法是阻塞方法，可以调用重载方法来马上获取是否有事件发生的结果
                if (selector.select(1000) == 0) {
                    System.out.println("服务器等待了1秒，没有事件发生");
                    continue;
                }
                //返回的事件集合，通过SelectionKey反向获取通道
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
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int byteNumber = socketChannel.read(byteBuffer);
                if (byteNumber > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String expression = new String(bytes, "UTF-8");
                    System.out.println("服务器收到消息：" + expression);
                    doWrite(socketChannel, "这个消息返回给你：" + expression);
                } else if (byteNumber < 0) {
                    selectionKey.cancel();
                    socketChannel.close();
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

    public void stop() {
        started = false;
    }
}
