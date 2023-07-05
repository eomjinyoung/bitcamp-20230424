package bitcamp.myapp;

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

    OutputStream out = socket.getOutputStream();
    InputStream in = socket.getInputStream();

    out.write(100);

    out.close();
    in.close();
    socket.close();
  }
}
