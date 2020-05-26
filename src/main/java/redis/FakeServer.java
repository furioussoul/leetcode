package redis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FakeServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(6380);
        final Socket accept = socket.accept();
        byte[] data = new byte[1024];
        accept.getInputStream().read(data);
        System.out.println(new String(data));
    }
}
