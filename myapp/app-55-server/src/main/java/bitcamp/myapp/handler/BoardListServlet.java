package bitcamp.myapp.handler;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/board/list")
public class BoardListServlet implements Servlet {

  BoardDao boardDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.parseInt(request.getParameter("category"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.printf("<a href='/board/form?category=%d'>새 글</a>\n", category);
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>");
    out.println("</thead>");

    List<Board> list = boardDao.findAll(category);

    out.println("<tbody>");
    for (Board board : list) {
      out.printf("<tr>"
          + " <td>%d</td>"
          + " <td><a href='/board/detail?category=%d&no=%d'>%s</a></td>"
          + " <td>%s</td>"
          + " <td>%d</td>"
          + " <td>%s</td></tr>\n",
          board.getNo(),
          board.getCategory(),
          board.getNo(),
          (board.getTitle().length() > 0 ? board.getTitle() : "제목없음"),
          board.getWriter().getName(),
          board.getViewCount(),
          dateFormatter.format(board.getCreatedDate())
          );
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }

}











