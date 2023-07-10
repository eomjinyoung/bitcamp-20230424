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
public class ServerApp03 {

  int port;
  ServerSocket serverSocket;

  HashMap<String,Object> daoMap = new HashMap<>();

  public ServerApp03(int port) throws Exception {
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

    ServerApp03 app = new ServerApp03(Integer.parseInt(args[0]));
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

      Method[] methods = dao.getClass().getDeclaredMethods();
      Method method = null;
      for (int i = 0; i < methods.length; i++) {
        if (methods[i].getName().equals(methodName)) {
          method = methods[i];
          break;
        }
      }

      if (method == null) {
        out.writeUTF(new ResponseEntity()
            .status(ResponseEntity.ERROR)
            .result("메서드를 찾을 수 없습니다.")
            .toJson());
        continue;
      }

      //      System.out.printf("%s.%s\n", dataName, methodName);

      // 메서드의 파라미터와 리턴 타입 알아내기
      Parameter[] params = method.getParameters();
      Class<?> returnType = method.getReturnType();

      if (params.length > 0) {
        System.out.println(params[0].getType().getSimpleName());
      }
      System.out.println(returnType.getSimpleName());


      ResponseEntity response = new ResponseEntity();

      switch (command) {
        //        case "board/list":
        //          response.status(ResponseEntity.SUCCESS).result(boardDao.list());
        //          break;
        //        case "board/insert":
        //          boardDao.insert(request.getObject(Board.class));
        //          response.status(ResponseEntity.SUCCESS);
        //          break;
        //        case "board/findBy":
        //          Board board = boardDao.findBy(request.getObject(Integer.class));
        //          if (board == null) {
        //            response.status(ResponseEntity.SUCCESS);
        //          } else {
        //            response.status(ResponseEntity.SUCCESS).result(board);
        //          }
        //          break;
        //        case "board/update":
        //          int value = boardDao.update(request.getObject(Board.class));
        //          response.status(ResponseEntity.SUCCESS).result(value);
        //          break;
        //        case "board/delete":
        //          value = boardDao.delete(request.getObject(Integer.class));
        //          response.status(ResponseEntity.SUCCESS).result(value);
        //          break;
        //        case "member/list":
        //          response.status(ResponseEntity.SUCCESS).result(memberDao.list());
        //          break;
        //        case "member/insert":
        //          memberDao.insert(request.getObject(Member.class));
        //          response.status(ResponseEntity.SUCCESS);
        //          break;
        //        case "member/findBy":
        //          Member member = memberDao.findBy(request.getObject(Integer.class));
        //          if (member == null) {
        //            response.status(ResponseEntity.SUCCESS);
        //          } else {
        //            response.status(ResponseEntity.SUCCESS).result(member);
        //          }
        //          break;
        //        case "member/update":
        //          value = memberDao.update(request.getObject(Member.class));
        //          response.status(ResponseEntity.SUCCESS).result(value);
        //          break;
        //        case "member/delete":
        //          value = memberDao.delete(request.getObject(Integer.class));
        //          response.status(ResponseEntity.SUCCESS).result(value);
        //          break;
        //        case "reading/list":
        //          response.status(ResponseEntity.SUCCESS).result(boardDao.list());
        //          break;
        //        case "reading/insert":
        //          boardDao.insert(request.getObject(Board.class));
        //          response.status(ResponseEntity.SUCCESS);
        //          break;
        //        case "reading/findBy":
        //          board = boardDao.findBy(request.getObject(Integer.class));
        //          if (board == null) {
        //            response.status(ResponseEntity.SUCCESS);
        //          } else {
        //            response.status(ResponseEntity.SUCCESS).result(board);
        //          }
        //          break;
        //        case "reading/update":
        //          value = boardDao.update(request.getObject(Board.class));
        //          response.status(ResponseEntity.SUCCESS).result(value);
        //          break;
        //        case "reading/delete":
        //          value = boardDao.delete(request.getObject(Integer.class));
        //          response.status(ResponseEntity.SUCCESS).result(value);
        //          break;
        default:
          response.status(ResponseEntity.ERROR).result("해당 명령을 지원하지 않습니다!");
      }

      out.writeUTF(response.toJson());
    }

    in.close();
    out.close();
    socket.close();
  }
}





