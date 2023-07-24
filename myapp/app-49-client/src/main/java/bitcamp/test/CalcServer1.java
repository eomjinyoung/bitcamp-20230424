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

      while (true) {
        processRequest(serverSocket.accept());
      }
    }
  }

  static void processRequest(Socket socket) {
    InetSocketAddress sockAddr = (InetSocketAddress) socket.getRemoteSocketAddress();
    System.out.printf("%s(%d) 클라이언트 접속!\n",
        sockAddr.getHostString(), sockAddr.getPort());

    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      int result = 0;

      while (true) {
        String op = in.readUTF();
        if (op.equals("quit")) {
          break;
        }

        int value = in.readInt();

        switch (op) {
          case "+": result += value; break;
          case "-": result -= value; break;
          case "*": result *= value; break;
          case "/": result /= value; break;
          case "%": result %= value; break;
          default: out.writeUTF("지원하지 않는 연산자입니다!");
        }

        out.writeUTF(String.format("%d", result));
      }
    } catch (Exception e) {
      System.out.printf("%s(%d) 클라이언트 통신 오류!\n",
          sockAddr.getHostString(), sockAddr.getPort());
    }
  }
}















