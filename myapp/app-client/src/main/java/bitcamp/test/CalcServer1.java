package bitcamp.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer1 {
  public static void main(String[] args) throws Exception {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행!");

      try (Socket socket = serverSocket.accept();
          DataInputStream in = new DataInputStream(socket.getInputStream());
          DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

        InetSocketAddress sockAddr = (InetSocketAddress) socket.getRemoteSocketAddress();
        System.out.printf("%s(%d) 클라이언트 접속!\n",
            sockAddr.getHostString(), sockAddr.getPort());

        while (true) {
          String op = in.readUTF();
          if (op.equals("quit")) {
            break;
          }

          int a = in.readInt();
          int b = in.readInt();

          switch (op) {
            case "+": out.writeUTF(String.format("%d", a + b)); break;
            case "-": out.writeUTF(String.format("%d", a - b)); break;
            case "*": out.writeUTF(String.format("%d", a * b)); break;
            case "/": out.writeUTF(String.format("%d", a / b)); break;
            case "%": out.writeUTF(String.format("%d", a % b)); break;
            default: out.writeUTF("지원하지 않는 연산자입니다!");
          }
        }
      }
    }
  }
}
