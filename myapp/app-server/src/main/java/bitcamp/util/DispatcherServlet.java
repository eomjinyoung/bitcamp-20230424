package bitcamp.util;

import java.io.PrintWriter;

public class DispatcherServlet implements Servlet {

  ApplicationContext iocContainer;

  public DispatcherServlet(ApplicationContext iocContainer) throws Exception {
    this.iocContainer = iocContainer;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Servlet servlet = (Servlet) iocContainer.getBean("/" + request.path());
    if (servlet == null) {
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("해당 요청을 처리할 수 없습니다!");
      return;
    }
    servlet.service(request, response);
  }
}
