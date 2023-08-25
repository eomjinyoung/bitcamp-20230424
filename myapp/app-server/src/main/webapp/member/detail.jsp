<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.Member"%>

<jsp:useBean id="memberDao" type="bitcamp.myapp.dao.MemberDao" scope="application"/>
<%
    Member member = memberDao.findBy(Integer.parseInt(request.getParameter("no")));
    pageContext.setAttribute("member", member);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원</h1>

<%
    if (member == null) {
%>
<p>해당 번호의 회원이 없습니다!</p>
<%
    } else {
%>
  <form action='/member/update.jsp' method='post' enctype='multipart/form-data'>
  <table border='1'>
  <tr>
      <th style='width:120px;'>사진</th>
      <td style='width:300px;'>
        <% if (member.getPhoto() == null) { %>
          <img style='height:80px' src='/images/avatar.png'>
        <% } else { %>
          <a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-118/member/${member.photo}'>
            <img src='http://mvsenqskbqzl19010704.cdn.ntruss.com/member/${member.photo}?type=f&w=60&h=80&faceopt=true&ttype=jpg'>
          </a>
        <% } %>
          <input type='file' name='photo'></td></tr>
  <tr>
      <th style='width:120px;'>번호</th>
      <td style='width:300px;'><input type='text' name='no' value='${member.no}' readonly></td></tr>
  <tr>
      <th>이름</th>
      <td><input type='text' name='name' value='${member.name}'></td></tr>
  <tr>
      <th>이메일</th>
      <td><input type='email' name='email' value='${member.email}'></td></tr>
  <tr>
      <th>암호</th>
      <td><input type='password' name='password'></td></tr>
  <tr>
      <th>성별</th>
      <td><select name='gender'>
          <option value='M' ${String.valueOf(member.getGender()) == 'M' ? "selected" : ""}>남자</option>
          <option value='W' ${String.valueOf(member.getGender()) == 'W' ? "selected" : ""}>여자</option></select></td></tr>
  </table>
  <div>
  <button>변경</button>
  <button type='reset'>초기화</button>
      <a href='/member/delete.jsp?no=${member.no}'>삭제</a>
  <a href='/member/list.jsp'>목록</a>
  </div>
  </form>
<%
    }
%>
<jsp:include page="../footer.jsp"/>

</body>
</html>
