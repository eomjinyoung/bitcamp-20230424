package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.vo.Member;
import bitcamp.util.AbstractServlet;

@WebServlet("/member/list")
public class MemberListServlet extends AbstractServlet {

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    HttpServletResponse response = (HttpServletResponse) res;

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/member/form.html'>새 회원</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>이름</th> <th>이메일</th></tr>");
    out.println("</thead>");

    List<Member> list = InitServlet.memberDao.findAll();
    for (Member m : list) {
      out.printf("<tr>"
          + " <td>%d</td>"
          + " <td><a href='/member/detail?no=%d'>%s</a></td>"
          + " <td>%s</td></tr>\n",
          m.getNo(), m.getNo(), m.getName(), m.getEmail());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }

}
