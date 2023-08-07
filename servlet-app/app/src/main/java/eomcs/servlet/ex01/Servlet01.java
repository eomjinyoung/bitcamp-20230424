package eomcs.servlet.ex01;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex01/first")
public class Servlet01 implements Servlet {

  ServletConfig config;

  public Servlet01() {
    System.out.println("Servlet01()");
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    // 서블릿 객체를 생성한 후 생성자 다음에 이 메서드가 호출된다.
    // => 서블릿을 실행할 때 사용할 자원을 이 메서드에서 준비한다.
    // => 파라미터로 받은 ServletConfig 객체는 인스턴스 변수에 보관해 두었다가 필요할 때 사용한다.
    // => 각각의 서블릿 클래스마다 객체는 한 개만 생성된다.
    // => 따라서 각 서블릿에 대해 init()는 한 번만 호출된다.
    this.config = config;
    System.out.println("Servlet01.init(ServletConfig)");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    HttpServletRequest req2 = (HttpServletRequest)req;
    HttpServletResponse res2 = (HttpServletResponse)res;
    // 클라이언트가 이 서블릿의 실행을 요청할 때마다 호출된다.
    // 클라이언트가 요청한 작업을 수행한다.
    System.out.println("Servlet01.service(ServletRequest,ServletResponse)");
  }

  @Override
  public void destroy() {
    // 웹 애플리케이션을 종료할 때(서버 종료 포함) 호출된다.
    // => 이 서블릿이 만든 자원을 해제하는 코드를 이 메서드에 둔다.
    System.out.println("Servlet01.destroy()");
  }

  @Override
  public ServletConfig getServletConfig() {
    // 서블릿에서 작업을 수행하는 중에 서블릿 관련 설정 정보를 꺼낼 때 이 메서드를 호출한다.
    // 이 메서드가 리턴하는 값이 ServletConfig 객체인데
    // 이 객체를 이용하면 서블릿 설정 정보를 알 수 있다.
    // 보통 init()의 파라미터 값을 리턴한다.
    System.out.println("Servlet01.getServletConfig()");
    return this.config;
  }

  @Override
  public String getServletInfo() {
    // 서블릿 컨테이너가 관리자 화면을 출력할 때 이 메서드를 호출한다.
    // 이 메서드의 리턴 값은 서블릿을 소개하는 간단한 문자열이다.
    System.out.println("Servlet01.getServletInfo()");
    return "Servlet01";
  }

}

