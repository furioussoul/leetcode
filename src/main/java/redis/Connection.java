package redis;

import redis.clients.jedis.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 传输层连接
 */
public class Connection {

    String host;
    int port;
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect(){
        try {
            this.socket = new Socket(host, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int sendCommand(RespProto.Command command, byte[] key, byte[] value){
        connect();
        RespProto.sendCommand(outputStream, command, key, value);
        return 1;
    }
}
