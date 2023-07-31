package bitcamp.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import bitcamp.net.NetProtocol;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DispatcherListener;
import bitcamp.util.MenuGroup;
import bitcamp.util.SqlSessionFactoryProxy;

public class ServerApp {

  // 자바 스레드풀 준비
  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  MenuGroup mainMenu = new MenuGroup("/", "메인");
  DispatcherListener facadeListener = new DispatcherListener();

  int port;

  public ServerApp(int port) throws Exception {
    this.port = port;
    prepareMenu();
  }

  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }

  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }

  private void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

      out.writeUTF("[나의 목록 관리 시스템]\n"
          + "-----------------------------------------");

      prompt.setAttribute("menuPath", "login");
      facadeListener.service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
      SqlSessionFactoryProxy sqlSessionFactoryProxy =
          (SqlSessionFactoryProxy) facadeListener.getBean("sqlSessionFactory");
      sqlSessionFactoryProxy.clean();
    }
  }

  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("member", "회원");
    memberMenu.add("member/add", "등록", facadeListener);
    memberMenu.add("member/list", "목록", facadeListener);
    memberMenu.add("member/detail", "조회", facadeListener);
    memberMenu.add("member/update", "변경", facadeListener);
    memberMenu.add("member/delete", "삭제", facadeListener);
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("board", "게시글");
    boardMenu.add("board/add", "등록", facadeListener);
    boardMenu.add("board/list", "목록", facadeListener);
    boardMenu.add("board/detail", "조회", facadeListener);
    boardMenu.add("board/update", "변경", facadeListener);
    boardMenu.add("board/delete", "삭제", facadeListener);
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("reading", "독서록");
    readingMenu.add("reading/add", "등록", facadeListener);
    readingMenu.add("reading/list", "목록", facadeListener);
    readingMenu.add("reading/detail", "조회", facadeListener);
    readingMenu.add("reading/update", "변경", facadeListener);
    readingMenu.add("reading/delete", "삭제", facadeListener);
    mainMenu.add(readingMenu);

  }
}