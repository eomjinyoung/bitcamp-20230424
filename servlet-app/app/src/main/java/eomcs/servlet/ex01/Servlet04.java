package eomcs.servlet.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex01/s4")
public class Servlet04 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("GET 요청 받음!");

    resp.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    out.println("GET 요청을 받았습니다!");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("POST 요청 받음!");

    resp.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    out.println("POST 요청을 받았습니다!");
  }
}
