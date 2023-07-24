package bitcamp.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

public class CalcServer3 {

  // 클라이언트 작업 결과를 보관할 저장소
  static HashMap<String,Integer> resultMap = new HashMap<>();

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
      String uuid = in.readUTF();

      if (uuid.length() == 0) {
        // 클라이언트가 처음 접속했다면, 클라이언트 식별 번호를 생성한다.
        uuid = UUID.randomUUID().toString();
      } else {
        // 처음 접속한 것이 아니라면, 이전에 접속했을 때 수행한 작업 결과를 가져온다.
        result = resultMap.get(uuid);
      }

      String op = in.readUTF();
      if (op.equals("quit")) {
        return;
      }

      int value = in.readInt();

      switch (op) {
        case "+": result += value; break;
        case "-": result -= value; break;
        case "*":
          result *= value;
          try {
            FileInputStream temp = new FileInputStream("temp/jls17.pdf");
            int b;
            while ((b = temp.read()) != -1) {
              Math.random();Math.random();Math.random();Math.random();Math.random();
            }
            temp.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case "/": result /= value; break;
        case "%": result %= value; break;
        default: out.writeUTF("지원하지 않는 연산자입니다!");
      }

      // 작업 결과를 저장소에 보관한다.
      resultMap.put(uuid, result);

      out.writeUTF(uuid);
      out.writeUTF(String.format("%d", result));

    } catch (Exception e) {
      System.out.printf("%s(%d) 클라이언트 통신 오류!\n",
          sockAddr.getHostString(), sockAddr.getPort());
    }
  }
}















