package com.myself.nio_timer_demo.server;


import io.netty.util.internal.StringUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimerServer implements Runnable {

    private Selector selector;

    private volatile boolean stop;

    private static int BUFFER_SIZE = 1024;

    /**
     * 初始化多路复用器，并绑定端口
     *
     * @param port
     */
    MultiplexerTimerServer(int port) {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), BUFFER_SIZE);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Timer server is start in port:" + port);
        } catch (Exception e) {
            System.out.println("Timer server is error!");
            System.exit(1);
        }
    }

    public void stopServer() {
        stop = true;
    }


    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    try {
                        handlerInput(selectionKey);
                    } catch (IOException e) {
                        selectionKey.cancel();
                        selectionKey.channel().close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Timer server runtime error");
            }
        }

        if (null != selector) {
            try {
                selector.close();
            } catch (IOException e) {
                System.out.println("Timer server close channel error");
            }
        }
    }

    private void handlerInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

            // accept 成功才表示三次握手成功，建立连接
            SocketChannel acceptChannel = serverSocketChannel.accept();
            acceptChannel.configureBlocking(false);

            acceptChannel.register(selector, SelectionKey.OP_READ);


            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer readerBuffer = ByteBuffer.allocate(BUFFER_SIZE);
                int readBytes = socketChannel.read(readerBuffer);
                if (readBytes > 0) {
                    readerBuffer.flip();
                    byte[] bytes = new byte[readerBuffer.remaining()];
                    readerBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Timer server receive order : " + body);
                    String currentTime = " query time".equalsIgnoreCase(body)
                            ? new Date(System.currentTimeMillis()).toString()
                            : "unknown request!";

                    // channel是全双工的，可以同时支持读和写
                    write(socketChannel, currentTime);
                } else if (readBytes < 0) {
                    // 返回-1，表示链路已经关闭，server也需要释放资源
                    key.cancel();
                    socketChannel.close();
                } else {
                    //do nothing
                }
            }
        }
    }

    /**
     * 通过channel将msg发送出去
     *
     * @param channel
     * @param msg
     * @throws IOException
     */
    private void write(SocketChannel channel, String msg) throws IOException {
        if (!StringUtil.isNullOrEmpty(msg)) {
            byte[] bytes = msg.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            // 这里会有半包问题
            channel.write(writeBuffer);
        }
    }
}