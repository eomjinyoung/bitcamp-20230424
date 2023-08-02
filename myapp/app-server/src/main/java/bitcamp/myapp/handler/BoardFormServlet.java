package bitcamp.myapp.handler;

import java.io.PrintWriter;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/board/form")
public class BoardFormServlet implements Servlet {

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
    out.println("<form action='/board/add'>");
    out.println("제목 <input type='text' name='title'><br>");
    out.println("내용 <textarea name='content'></textarea><br>");
    out.printf("<input type='hidden' name='category' value='%d'>\n", category);
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}











