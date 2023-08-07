package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.util.AbstractServlet;

@WebServlet("/board/form")
public class BoardFormServlet extends AbstractServlet {

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    int category = Integer.parseInt(request.getParameter("category"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글</h1>");
    out.println("<form action='/board/add' method='post'>");
    out.println("제목 <input type='text' name='title'><br>");
    out.println("내용 <textarea name='content'></textarea><br>");
    out.printf("<input type='hidden' name='category' value='%d'>\n", category);
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");

  }
}











