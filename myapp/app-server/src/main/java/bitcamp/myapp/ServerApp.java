package bitcamp.myapp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(8888);
    System.out.println("서버 실행 중...");

    Socket socket = serverSocket.accept();

    InputStream in = socket.getInputStream();
    OutputStream out = socket.getOutputStream();

    int b = in.read();
    System.out.println(b);

    in.close();
    out.close();
    socket.close();
    serverSocket.close();
  }
}
