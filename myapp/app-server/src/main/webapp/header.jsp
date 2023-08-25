<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<div style='height:50px;background-color:orange;'>
    <img src='https://www.ncloud.com/public/img/logo-m.png' style='height:40px'>
    <a href='/member/list.jsp'>회원</a>
    <a href='/board/list.jsp?category=1'>게시글</a>
    <a href='/board/list.jsp?category=2'>독서록</a>

<jsp:useBean id="loginUser" class="bitcamp.myapp.vo.Member" scope="session"/>
<% if (loginUser.getNo() == 0) { %>
     <a href='/auth/form.jsp'>로그인</a>
<% } else {
     if (loginUser.getPhoto() == null) { %>
       <img style='height:40px' src='/images/avatar.png'>
  <% } else { %>
       <img src='http://mvsenqskbqzl19010704.cdn.ntruss.com/member/${loginUser.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
  <% } %>
       ${loginUser.name} <a href='/auth/logout.jsp'>로그아웃</a>
<% } %>
</div>