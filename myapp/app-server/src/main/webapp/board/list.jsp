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

<h1>게시글 목록-2</h1>
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
%>
<tbody>
<%
  for (Board board : list) {
%>
    <tr>
      <td><%=board.getNo()%></td>
      <td><a href='/board/detail.jsp?category=<%=board.getCategory()%>&no=<%=board.getNo()%>'>
        <%=(board.getTitle().length() > 0 ? board.getTitle() : "제목없음")%>
        </a>
      </td>
      <td><%=board.getWriter().getName()%></td>
      <td><%=board.getViewCount()%></td>
      <td><%=dateFormatter.format(board.getCreatedDate())%></td>
    </tr>
<%
  }
%>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>