package bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {

    public static void main(String[] args) throws IOException {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                });
        final ServerSocket socket = new ServerSocket(8081);

        while(true){
            final Socket accept = socket.accept();
            threadPoolExecutor.execute(new Runnable() {
               @Override
               public void run() {
                   try {
                       final InputStream inputStream = accept.getInputStream();
                       final OutputStream outputStream = accept.getOutputStream();
                       final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                       final PrintStream printStream = new PrintStream(outputStream);
                       String line = null;
                       while((line = bufferedReader.readLine())!= null){
                           //业务逻辑
                           printStream.println("server rcv : " + line);
                       }
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           });
        }

    }
}
