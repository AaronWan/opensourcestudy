package basic.study.oldServer;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aaron on 21/07/2017.
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            Socket finalSocket = socket;
            System.out.println("connect by "+socket.getInetAddress().getAddress());
            executor.execute(() -> {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(finalSocket.getInputStream()));
                    System.out.println(br.readLine());
                    PrintWriter pw = new PrintWriter(finalSocket.getOutputStream());
                    for (int i = 0; i < 10; i++) {
                        TimeUnit.SECONDS.sleep(2);
                        pw.println(i+"test");
                        pw.flush();
                    }
                    finalSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
