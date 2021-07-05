package com.myself.nio_demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;


public class ServerDemo {

    private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
    private Selector selector;
    private static int PORT = 8080;

    private ServerDemo() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);
        System.out.println("listening on port " + PORT);
        this.selector = Selector.open();
        // 绑定channel的accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws Exception {
        new ServerDemo().run();
    }

    private void run() throws Exception {

        // block api
        while (selector.select() > 0) {

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 删除最后一个数据，可以理解为clear或init，该方法只能调用一次
                iterator.remove();
                // 新连接
                if (selectionKey.isAcceptable()) {
                    System.out.println("the channel is Acceptable");
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

                    // 新注册channel
                    SocketChannel socketChannel = server.accept();
                    if (socketChannel == null) {
                        continue;
                    }
                    // 设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 注册读和写事件
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                    buffer.put("hi new channel".getBytes());
                    // 翻转缓冲区 ？什么作用 表示读或者写操作从buffer的头开始读，
                    // 也就是说调用flip之后，读写指针指到缓存头部，并且设置了最多只能读出之前写入的数据长度(而不是整个缓存的容量大小)
                    buffer.flip();
                    socketChannel.write(buffer);
                }

                // 服务端关心的可读，意味着有数据从client传来了，根据不同的需要进行读取，然后返回
                if (selectionKey.isReadable()) {
                    System.out.println("isReadable");
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    readBuffer.flip();

                    String receiveData = StandardCharsets.UTF_8.decode(readBuffer).toString();
                    System.out.println("receiveData:" + receiveData);

                    // 把读到的数据绑定到key中
                    selectionKey.attach("server message echo:" + receiveData);
                }

                // 实际上服务端不在意这个，这个写入应该是client端关心的，这只是个demo,顺便试一下selectionKey的attach方法
                if (selectionKey.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    String message = (String) selectionKey.attachment();
                    if (message == null) {
                        continue;
                    }
                    selectionKey.attach(null);

                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();
                    while (writeBuffer.hasRemaining()) {
                        socketChannel.write(writeBuffer);
                    }
                }
            }
        }
    }

}
