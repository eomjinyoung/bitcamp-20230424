<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="refresh" value="2;url=list.jsp?category=${param.category}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시글</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>게시글</h1>

<c:if test="${empty board}">
    <p>해당 번호의 게시글이 없습니다!</p>
</c:if>

<c:if test="${not empty board}">
    <form action='/board/update' method='post' enctype='multipart/form-data'>
    <input type='hidden' name='category' value='${board.category}'>
    <table border='1'>
    <tr><th style='width:120px;'>번호</th>
    <td style='width:300px;'><input type='text' name='no' value='${board.no}' readonly></td></tr>
    <tr><th>제목</th>
    <td><input type='text' name='title' value='${board.title}'></td></tr>
    <tr><th>내용</th>
    <td><textarea name='content' style='height:200px; width:400px;'>${board.content}</textarea></td></tr>
    <tr><th>작성자</th> <td>${board.writer.name}</td></tr>
    <tr><th>조회수</th> <td>${board.viewCount}</td></tr>
    <tr><th>등록일</th> <td>${simpleDateFormatter.format(board.createdDate)}</td></tr>
    <tr><th>첨부파일</th><td>
    <c:forEach items="${board.attachedFiles}" var="file">
        <a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-118/board/${file.filePath}'>${file.filePath}</a>
        [<a href='/board/fileDelete.jsp?category=${param.category}&no=${file.no}'>삭제</a>]<br>
    </c:forEach>
        <input type='file' name='files' multiple>
    </td></tr>
    </table>

    <div>
    <button>변경</button>
    <button type='reset'>초기화</button>
    <a href='/board/delete?category=${param.category}&no=${param.no}'>삭제</a>
    <a href='/board/list?category=${param.category}'>목록</a>
    </div>
    </form>
</c:if>

<jsp:include page="../footer.jsp"/>

</body>
</html>













