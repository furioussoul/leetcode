package io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class BlockingIo {
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    public static void main(String[] args) {
        try {
            // 1.创建一个ServerSocketChannel连接
            final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 2.绑定端口号
            serverSocketChannel.bind(new InetSocketAddress(8081));
            // 设置为非阻塞式
            serverSocketChannel.configureBlocking(true);
            // 非阻塞式
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                int j = socketChannel.read(byteBuffer);
                if (j > 0) {
                    byte[] bytes = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
                    System.out.println("获取到数据" + new String(bytes));
                }
            }
            System.out.println("程序执行完毕..");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
