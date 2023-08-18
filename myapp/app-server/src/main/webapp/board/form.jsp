<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>비트캠프</title>
  </head>
  <body>
    <h1>게시글(JSP)</h1>
    <form action="/board/add" method="post" enctype="multipart/form-data">
      제목 <input type="text" name="title" /><br />
      내용 <textarea name="content"></textarea><br />
      파일 <input type="file" name="files" multiple /><br />
      <input type="hidden" name="category" value='<%=request.getParameter("category")%>' />
      <button>등록</button>
    </form>
  </body>
</html>
