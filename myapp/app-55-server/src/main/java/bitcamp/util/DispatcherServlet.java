package bitcamp.util;

import java.io.PrintWriter;

public class DispatcherServlet implements Servlet {

  ApplicationContext iocContainer;

  public DispatcherServlet(ApplicationContext iocContainer) throws Exception {
    this.iocContainer = iocContainer;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      // 서블릿의 실행을 요구할 경우,
      Servlet servlet = (Servlet) iocContainer.getBean(request.getServletPath());
      if (servlet == null) {
        throw new Exception("요청한 URL이 유효하지 않습니다.");
      }

      servlet.service(request, response);

    } catch (Exception e) {
      e.printStackTrace();
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
}







