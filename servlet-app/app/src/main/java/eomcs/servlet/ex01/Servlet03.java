package eomcs.servlet.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ex01/s3")
public class Servlet03 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("Servlet03.service() 호출됨!");

    HttpSession session =  req.getSession();
    resp.sendRedirect("/ex01/s2");
  }
}







