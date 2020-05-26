package redis;

public class Client {

    Connection connection;

    public Client(String host, int port) {
        this.connection = new Connection(host, port);
    }

    public void sendCommand(RespProto.Command command, String key, String value){
        connection.sendCommand(command, SafeEncode.encode(key), SafeEncode.encode(value));
    }

    public void set(String key, String value){
        sendCommand(RespProto.Command.SET, key, value);
    }

    public void get(RespProto.Command command, String key, String value){
        sendCommand(RespProto.Command.GET, key, value);
    }

    public static void main(String[] args) {
        final Client client = new Client("localhost", 6380);
        client.set("a", "b");
    }
}
