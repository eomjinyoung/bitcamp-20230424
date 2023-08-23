<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%> <%-- directive element --%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.myapp.dao.BoardDao"%>
<%@ page import="bitcamp.myapp.vo.Board"%>
<%@ page import="bitcamp.util.NcpObjectStorageService"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>

<%!
  // declaration element
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
%>

<%
  // scriptlet (scripting element)
  int category = Integer.parseInt(request.getParameter("category"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시글</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>게시글 목록-1</h1>
<div style='margin:5px;'>
<a href='/board/form.jsp?category=<%=category%>'>새 글</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>
</thead>

<%
  BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  List<Board> list = boardDao.findAll(category);

  out.println("<tbody>");
  for (Board board : list) {
      out.println(String.format("<tr>"
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
          ));
  }
%>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>