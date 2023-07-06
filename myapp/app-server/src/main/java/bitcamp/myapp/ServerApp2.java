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

public class ServerApp2 {

  int port;
  ServerSocket serverSocket;

  HashMap<String,Object> daoMap = new HashMap<>();

  public ServerApp2(int port) throws Exception {
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

    ServerApp2 app = new ServerApp2(Integer.parseInt(args[0]));
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

      CommandHandler commandHandler = getCommandHandler(command);

      ResponseEntity response = new ResponseEntity();
      if (commandHandler != null) {
        Parameter[] params = commandHandler.method.getParameters();
        Class<?> returnType = commandHandler.method.getReturnType();

        Object[] args = null;
        if (params.length == 0) {
          args = new Object[0];
        } else {
          args = new Object[] {request.getObject(params[0].getType())};
        }

        if (returnType == void.class) {
          commandHandler.method.invoke(commandHandler.handler, args);
          response.status(ResponseEntity.SUCCESS);
        } else {
          Object result = commandHandler.method.invoke(commandHandler.handler, args);
          response.status(ResponseEntity.SUCCESS).result(result);
        }

      } else {
        response.status(ResponseEntity.ERROR).result("해당 명령을 지원하지 않습니다!");
      }

      out.writeUTF(response.toJson());
    }

    in.close();
    out.close();
    socket.close();
  }

  private CommandHandler getCommandHandler(String command) {
    String[] values = command.split("/");
    Object dao = daoMap.get(values[0]);
    if (dao == null) {
      return null;
    }

    Method[] methods = dao.getClass().getDeclaredMethods();
    for (Method method : methods) {
      if (method.getName().equals(values[1])) {
        return new CommandHandler(dao, method);
      }
    }
    return null;
  }

  static class CommandHandler {
    Object handler;
    Method method;

    public CommandHandler(Object handler, Method method) {
      this.handler = handler;
      this.method = method;
    }
  }
}





