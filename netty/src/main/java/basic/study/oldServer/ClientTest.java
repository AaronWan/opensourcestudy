package basic.study.oldServer;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aaron on 21/07/2017.
 */
public class ClientTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8080);
//向本机的4700端口发出客户请求
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
//由系统标准输入设备构造BufferedReader对象
        PrintWriter os = new PrintWriter(socket.getOutputStream());
//由Socket对象得到输出流，并构造PrintWriter对象
        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//由Socket对象得到输入流，并构造相应的BufferedReader对象
        String readline;
        readline = sin.readLine(); //从系统标准输入读入一字符串
        while (!readline.equals("bye")) {
            os.println(readline);
//将从系统标准输入读入的字符串输出到Server
            os.flush();
//刷新输出流，使Server马上收到该字符串
            System.out.println("Client:" + readline);
//在系统标准输出上打印读入的字符串
            System.out.println("Server:" + is.readLine());
//从Server读入一字符串，并打印到标准输出上
            readline = sin.readLine(); //从系统标准输入读入一字符串
        } //继续循环
        os.close(); //关闭Socket输出流
        is.close(); //关闭Socket输入流
        socket.close(); //关闭Socket
    }
}
