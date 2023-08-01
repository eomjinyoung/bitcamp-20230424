package bitcamp.util;

import java.io.PrintWriter;

public class DispatcherServlet implements Servlet {

  ApplicationContext iocContainer;

  public DispatcherServlet(ApplicationContext iocContainer) throws Exception {
    this.iocContainer = iocContainer;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PrintWriter out = response.getWriter();
    out.println("Hello, world!");
    out.println("반가워요!");

    //    ActionListener listener = (ActionListener) iocContainer.getBean((String)prompt.getAttribute("menuPath"));
    //    if (listener == null) {
    //      throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
    //    }
    //    listener.service(prompt);
  }
}
