package bitcamp.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import bitcamp.myapp.dao.BoardListDao;
import bitcamp.myapp.dao.MemberListDao;
import bitcamp.net.RequestEntity;
import bitcamp.net.ResponseEntity;

// 1) 클라이언트가 보낸 명령을 데이터이름과 메서드 이름으로 분리한다.
// 2) 클라이언트가 요청한 DAO 객체와 메서드를 찾는다.
// 3) 메서드의 파라미터와 리턴 타입을 알아내기
// 4) 메서드 호출 및 리턴 값 받기
// 5) 리팩토링
public class ServerApp05 {

  int port;
  ServerSocket serverSocket;

  HashMap<String,Object> daoMap = new HashMap<>();

  public ServerApp05(int port) throws Exception {
    this.port = port;

    daoMap.put("member", new MemberListDao("member.json"));
    daoMap.put("board", new BoardListDao("board.json"));
    daoMap.put("reading", new BoardListDao("reading.json"));
  }

  public void close() throws Exception {
    serverSocket.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("실행 예) java ... bitcamp.myapp.ServerApp 포트번호");
      return;
    }

    ServerApp05 app = new ServerApp05(Integer.parseInt(args[0]));
    app.execute();
    app.close();
  }

  public void execute() throws Exception {
    System.out.println("[MyList 서버 애플리케이션]");

    this.serverSocket = new ServerSocket(port);
    System.out.println("서버 실행 중...");

    Socket socket = serverSocket.accept();
    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    while (true) {
      RequestEntity request = RequestEntity.fromJson(in.readUTF());

      String command = request.getCommand();
      System.out.println(command);

      if (command.equals("quit")) {
        break;
      }

      String[] values = command.split("/");
      String dataName = values[0];
      String methodName = values[1];

      Object dao = daoMap.get(dataName);
      if (dao == null) {
        out.writeUTF(new ResponseEntity()
            .status(ResponseEntity.ERROR)
            .result("데이터를 찾을 수 없습니다.")
            .toJson());
        continue;
      }

      // DAO 객체에서 메서드 찾기
      Method method = findMethod(dao, methodName);
      if (method == null) {
        out.writeUTF(new ResponseEntity()
            .status(ResponseEntity.ERROR)
            .result("메서드를 찾을 수 없습니다.")
            .toJson());
        continue;
      }

      // DAO 메서드 호출하기
      Object result = call(dao, method, request);

      ResponseEntity response = new ResponseEntity();
      response.status(ResponseEntity.SUCCESS);
      response.result(result);
      out.writeUTF(response.toJson());
    }

    in.close();
    out.close();
    socket.close();
  }

  // 메서드 찾기
  public static Method findMethod(Object obj, String methodName) {
    Method[] methods = obj.getClass().getDeclaredMethods();
    for (int i = 0; i < methods.length; i++) {
      if (methods[i].getName().equals(methodName)) {
        return methods[i];
      }
    }
    return null;
  }

  // 메서드 호출하기
  public static Object call(Object obj, Method method, RequestEntity request) throws Exception {
    Parameter[] params = method.getParameters();
    if (params.length > 0) {
      return method.invoke(obj, request.getObject(params[0].getType()));
    } else {
      return method.invoke(obj);
    }
  }
}





