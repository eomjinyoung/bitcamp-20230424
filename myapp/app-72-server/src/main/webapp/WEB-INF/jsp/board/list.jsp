<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시글</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>게시글 목록</h1>
<div style='margin:5px;'>
<a href='add?category=${param.category}'>새 글</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>
</thead>

<tbody>
<c:forEach items="${list}" var="board">
    <tr>
      <td>${board.no}</td>
      <td><a href='detail?no=${board.no}'>
        ${board.title.length() > 0 ? board.title : "제목없음"}
        </a>
      </td>
      <td>${board.writer.name}</td>
      <td>${board.viewCount}</td>
      <td><fmt:formatDate value="${board.createdDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>