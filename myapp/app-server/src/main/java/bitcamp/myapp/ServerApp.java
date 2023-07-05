package bitcamp.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(8888);
    System.out.println("서버 실행 중...");

    Socket socket = serverSocket.accept();

    InputStream in0 = socket.getInputStream();
    DataInputStream in = new DataInputStream(in0);

    OutputStream out0 = socket.getOutputStream();
    DataOutputStream out = new DataOutputStream(out0);

    System.out.println(in.readUTF());

    in.close();
    out.close();
    socket.close();
    serverSocket.close();
  }
}
