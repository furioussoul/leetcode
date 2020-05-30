package io;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) {

        Socket echoSocket = null;
		PrintStream out = null;
        BufferedReader in = null;

        try {

            echoSocket = new Socket("127.0.0.1", 8081);
            out = new PrintStream(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
            System.out.println("连接到服务器......");
            System.out.println("请输入消息[输入\"Quit\"]退出：");

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(
                    System.in));
            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println(in.readLine());

                if (userInput.equals("Quit")) {
                    System.out.println("关闭客户端......");
                    out.close();
                    in.close();
                    stdIn.close();
                    echoSocket.close();
                    System.exit(1);
                }
                System.out.println("请输入消息[输入\"Quit\"]退出：");
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: PallaviÕs MacBook Pro.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: PallaviÕs MacBook Pro.");
            System.exit(1);
        }

    }

}
