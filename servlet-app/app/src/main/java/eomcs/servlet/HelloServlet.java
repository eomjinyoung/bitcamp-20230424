package eomcs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/hello")
public class HelloServlet implements Servlet {

  ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {
    // 서블릿 컨테이너(톰캣)가 인스턴스를 생성한 후 호출함.
    // => 이 메서드에서 서블릿 실행에 필요한 것들을 준비하면 된다.

    System.out.println("HelloServlet.init() 호출됨!");
    this.config = config;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    // 클라이언트가 요청할 때 마다 서블릿 컨테이너가 호출함.
    // => 이 메서드에서 클라이언트 요청을 처리한 후 그 결과를 리턴하면 된다.
    System.out.println("HelloServlet.service() 호출됨!");

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>환영!</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Hello, world!</h1>");
    out.println("</body>");
    out.println("</html>");

  }

  @Override
  public void destroy() {
    // 서블릿 컨테이너가 종료되기 직전에 호출함.
    // => init()에서 준비한 자원을 해제시키면 된다.
  }

  @Override
  public String getServletInfo() {
    // 서블릿의 간단한 설명이 필요할 때 호출할 수 있음.
    // 서블릿 컨테이너가 관리 화면에 출력하기 위해 호출할 수 있고,
    // 다른 서블릿에서 호출할 수도 있다.

    return "Hello~~~~ Servlet!";
  }

  @Override
  public ServletConfig getServletConfig() {
    // 서블릿에 대한 설정 정보를 담은 객체를 리턴한다.
    // 즉 서블릿과 관련된 설정 정보를 알고자 할 때 이 메서드를 호출하여 그 리턴 값을 사용한다.

    return this.config;
  }
}




