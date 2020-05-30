package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws IOException {
        final Selector selector = Selector.open();
        final ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(8081), 1024);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (!Thread.interrupted()) {
            selector.select(1000);
            final Set<SelectionKey> keys = selector.selectedKeys();
            final Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                final SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        final ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        final SocketChannel channel = ssc.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        final SocketChannel sc = (SocketChannel) key.channel();
                        final ByteBuffer readBuff = ByteBuffer.allocate(1024);
                        final int readBytes = sc.read(readBuff);

                        if (readBytes > 0) {
                            readBuff.flip();
                            byte[] bytes = new byte[readBuff.remaining()];
                            readBuff.get(bytes);
                            final String body = new String(bytes, StandardCharsets.UTF_8);
                            System.out.println("来自客户端的请求：" + body);

                            if (body.equalsIgnoreCase("quit")) {
                                System.out.println("关闭与客户端的连接......");
                                sc.close();
                                key.cancel();
                            } else {
                                final ByteBuffer byteBuffer = ByteBuffer.wrap("来自服务端的响应".getBytes());
                                sc.write(byteBuffer);
                            }
                        } else if (readBytes < 0) {
                            // 对端链路关闭
                            key.cancel();
                            sc.close();
                        }
                    }
                }
            }
        }

        selector.close();
    }
}
