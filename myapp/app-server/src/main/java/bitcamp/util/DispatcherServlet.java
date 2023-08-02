package bitcamp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import bitcamp.myapp.dao.MemberDao;

public class DispatcherServlet implements Servlet {

  ApplicationContext iocContainer;

  public DispatcherServlet(ApplicationContext iocContainer) throws Exception {
    this.iocContainer = iocContainer;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      // 지정한 경로의 기본 문서를 요청할 경우,
      if (request.getServletPath().endsWith("/")) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(getStaticContent(request.getServletPath() + "index.html"));
        return;
      }

      // HTML 문서를 요청할 경우,
      if (request.getServletPath().endsWith(".html")) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(getStaticContent(request.getServletPath()));
        return;
      }

      // 서블릿의 실행을 요구할 경우,
      Servlet servlet = (Servlet) iocContainer.getBean(request.getServletPath());
      if (servlet == null) {
        throw new Exception("요청한 URL이 유효하지 않습니다.");
      }

      MemberDao memberDao = iocContainer.getBean(MemberDao.class);
      request.setAttribute("loginUser", memberDao.findBy(3));
      servlet.service(request, response);

    } catch (Exception e) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>게시글</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>실행 오류!</h1>");
      out.printf("<p>%s</p>\n", e.getMessage());
      out.println("</body>");
      out.println("</html>");
    }
  }

  private String getStaticContent(String url) throws Exception {
    // 파일에서 읽은 데이터를 담을 버퍼 객체
    StringBuilder strBuf = new StringBuilder();

    // 클라이언트가 요청한 URL의 실제 파일 위치를 알아낸다.
    String realPath = getRealPath(url);
    try (BufferedReader in = new BufferedReader(new FileReader(realPath))) {
      int ch;
      while ((ch = in.read()) != -1) {
        // 파일에서 한 줄씩 읽어서 버퍼에 담는다.
        strBuf.append((char)ch);
      }
    }

    // 버퍼에 담은 모든 데이터를 리턴한다.
    // 즉 파일에서 읽은 데이터를 리턴하는 것이다.
    return strBuf.toString();
  }

  private String getRealPath(String urlPath) throws Exception {
    // 일반 파일(static resource)은 "static" 이라는 이름의 패키지에 있기 때문에
    // 일반 파일을 찾을 때는 static 패키지에서 찾아야 한다.
    String staticResourcePath = "/static" + urlPath;

    // 클래스 정보를 담고 있는 객체를 알아낸다.
    // 어떤 클래스라도 상관없다.
    // 일반 파일이 놓여 있는 classpath에 존재하는 클래스면 된다.
    Class<?> clazz = DispatcherServlet.class;

    // Class.getResource()는 주어진 경로의 파일의 실제 위치를 찾아 준다.
    URL fileURL = clazz.getResource(staticResourcePath);

    // URL 객체에서 파일 경로를 문자열로 추출하여 리턴한다.
    return fileURL.getFile();
  }
}







