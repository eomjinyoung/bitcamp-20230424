<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.myapp.vo.Board"%>

<%
  request.setAttribute("refresh", "2;url=list.jsp?category=" + request.getParameter("category"));
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
<a href='/board/form.jsp?category=${param.category}'>새 글</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>
</thead>

<jsp:useBean id="boardDao" type="bitcamp.myapp.dao.BoardDao" scope="application"/>

<%
  List<Board> list = boardDao.findAll(category);
%>
<tbody>
<%
  for (Board board : list) {
    pageContext.setAttribute("board", board);
%>
    <tr>
      <td>${board.no}</td>
      <td><a href='/board/detail.jsp?category=${board.category}&no=${board.no}'>
        ${board.title.length() > 0 ? board.title : "제목없음"}
        </a>
      </td>
      <td>${board.writer.name}</td>
      <td>${board.viewCount}</td>
      <td>${simpleDateFormatter.format(board.createdDate)}</td>
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