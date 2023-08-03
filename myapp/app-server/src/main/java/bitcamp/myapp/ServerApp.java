package bitcamp.myapp;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.config.AppConfig;
import bitcamp.util.ApplicationContext;
import bitcamp.util.DispatcherServlet;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.HttpSession;
import bitcamp.util.SqlSessionFactoryProxy;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

public class ServerApp {

  public static final String MYAPP_SESSION_ID = "myapp_session_id";

  ApplicationContext iocContainer;
  DispatcherServlet dispatcherServlet;
  Map<String,HttpSession> sessionMap = new HashMap<>();

  int port;

  public ServerApp(int port) throws Exception {
    this.port = port;
    iocContainer = new ApplicationContext(AppConfig.class);
    dispatcherServlet = new DispatcherServlet(iocContainer);
  }

  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }

  public void execute() throws Exception {
    DisposableServer server = HttpServer
        .create()
        .port(8888)
        .handle((request, response) -> processRequest(request, response))
        .bindNow();
    System.out.println("서버 실행됨!");

    server.onDispose().block();
    System.out.println("서버 종료됨!");
  }

  private NettyOutbound processRequest(HttpServerRequest request, HttpServerResponse response) {

    HttpServletRequest request2 = new HttpServletRequest(request);
    HttpServletResponse response2 = new HttpServletResponse(response);

    try {
      // 클라이언트 세션 ID 알아내기
      String sessionId = null;
      boolean firstVisit = false;

      // 클라이언트가 보낸 쿠키들 중에서 세션ID가 있는지 확인한다.
      List<Cookie> cookies = request2.allCookies().get(MYAPP_SESSION_ID);
      if (cookies != null) {
        // 세션ID가 있으면 이 값을 가지고 클라이언트를 구분한다.
        sessionId = cookies.get(0).value();
      } else {
        // 세션ID가 없으면 이 클라이언트를 구분하기 위해 새 세션ID를 발급한다.
        sessionId = UUID.randomUUID().toString();
        firstVisit = true;
      }

      // 세션ID로 클라이언트에게 배정된 HttpSession 객체를 찾는다.
      HttpSession session = sessionMap.get(sessionId);
      if (session == null) {
        // 현재 클라이언트가 사용할 HttpSession 객체가 배정되지 않았다면, 새로 만든다.
        session = new HttpSession(sessionId);

        // 새로 만든 세션 객체를 세션ID를 사용하여 맵에 보관한다.
        sessionMap.put(sessionId, session);
      }

      // 서블릿에서 HttpSession 보관소를 사용할 수 있도록 HttpServletRequest에 담아 둔다.
      request2.setSession(session);

      if (firstVisit) {
        // 세션ID가 없는 클라이언트를 위해 새로 발급한 세션ID를 쿠키로 보낸다.
        // 웹브라우저는 이 값을 내부 메모리에 저장할 것이다.
        response.addCookie(new DefaultCookie(MYAPP_SESSION_ID, sessionId));
      }

      String servletPath = request2.getServletPath();

      // favicon.ico 요청에 대한 응답
      if (servletPath.equals("/favicon.ico")) {
        response.addHeader("Content-Type", "image/vnd.microsoft.icon");
        return response.sendFile(Path.of(ServerApp.class.getResource("/static/favicon.ico").toURI()));
      }

      // welcome 파일 또는 HTML 파일을 요청할 때
      if (servletPath.endsWith("/") || servletPath.endsWith(".html")) {
        String resourcePath = String.format("/static%s%s",
            servletPath,
            (servletPath.endsWith("/") ? "index.html" : ""));

        response.addHeader("Content-Type", "text/html;charset=UTF-8");
        return response.sendFile(Path.of(ServerApp.class.getResource(resourcePath).toURI()));
      }

      if (request.isFormUrlencoded()) {
        // POST 방식으로 요청했다면,
        return response.sendString(request.receive()
            .aggregate()
            .asString()
            .map(body -> {
              try {
                request2.parseFormBody(body);
                dispatcherServlet.service(request2, response2);
              } catch (Exception e) {
                e.printStackTrace();
              }
              response.addHeader("Content-Type", response2.getContentType());
              return response2.getContent();
            }));

      } else {
        // GET 방식으로 요청했다면,
        dispatcherServlet.service(request2, response2);
        response.addHeader("Content-Type", response2.getContentType());
        return response.sendString(Mono.just(response2.getContent()));
      }

    } catch (Exception e) {
      e.printStackTrace();
      return response.sendString(Mono.just(response2.getContent()));

    } finally {
      SqlSessionFactoryProxy sqlSessionFactoryProxy =
          (SqlSessionFactoryProxy) iocContainer.getBean(SqlSessionFactory.class);
      sqlSessionFactoryProxy.clean();
    }

  }

}