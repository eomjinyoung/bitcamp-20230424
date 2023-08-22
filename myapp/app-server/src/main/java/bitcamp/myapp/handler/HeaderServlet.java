package bitcamp.myapp.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div style='height:50px;background-color:orange;'>");
    out.println("<img src='https://www.ncloud.com/public/img/logo-m.png' style='height:40px'>");
    out.println("<a href='/member/list'>회원</a>");
    out.println("<a href='/board/list?category=1'>게시글</a>");
    out.println("<a href='/board/list?category=2'>독서록</a>");
    out.println("<a href='/auth/form.html'>로그인</a>");
    out.println("</div>");
  }
}











