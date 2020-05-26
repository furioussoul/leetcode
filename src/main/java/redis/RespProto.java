package redis;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 网络层，使用resp协议
 */
public class RespProto {

    public final static String DOLLAR_BYTE ="$";
    public final static String ASTERISK_BYTE ="*";
    public final static String BLANK_BYTE ="\r\n";

    public static void sendCommand(OutputStream outputStream, Command command, byte[]... bytes){
        final StringBuilder sb = new StringBuilder();
        sb.append(ASTERISK_BYTE).append(bytes.length+1).append(BLANK_BYTE);
        sb.append(DOLLAR_BYTE).append(command.name().length()).append(BLANK_BYTE);
        sb.append(command).append(BLANK_BYTE);

        for (byte[] bArray : bytes) {
            sb.append(DOLLAR_BYTE).append(bArray.length+1).append(BLANK_BYTE);
            sb.append(new String(bArray)).append(BLANK_BYTE);
        }

        try {
            outputStream.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static enum Command {
        SET,GET;
    }
}
