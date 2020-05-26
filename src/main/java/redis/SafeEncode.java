package redis;

public class SafeEncode {

    public static byte[] encode(String s){
        return s.getBytes();
    }

    public static String decode(byte[] bytes){
        return new String(bytes);
    }
}
