package bitcamp.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientApp {

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("실행 예) java -cp bin/main bitcamp.myapp.ClientApp 서버주소");
      return;
    }

    Socket socket = new Socket(args[0], 8888);

    OutputStream out0 = socket.getOutputStream();
    DataOutputStream out = new DataOutputStream(out0);

    InputStream in0 = socket.getInputStream();
    DataInputStream in = new DataInputStream(in0);

    out.writeUTF("안녕! Hello!");

    out.close();
    in.close();
    socket.close();
  }
}
