<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ page import="bitcamp.myapp.vo.Member"%>

<div style='height:50px;background-color:orange;'>
    <img src='https://www.ncloud.com/public/img/logo-m.png' style='height:40px'>
    <a href='/member/list'>회원</a>
    <a href='/board/list?category=1'>게시글</a>
    <a href='/board/list?category=2'>독서록</a>

<%
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<a href='/auth/form'>로그인</a>");
    } else {
      if (loginUser.getPhoto() == null) {
        out.println("<img style='height:40px' src='/images/avatar.png'>");
      } else {
        out.println(String.format(
          "<img src='http://mvsenqskbqzl19010704.cdn.ntruss.com/member/%s?type=f&w=30&h=40&faceopt=true&ttype=jpg'>",
                                                  loginUser.getPhoto()));
      }
      out.println(String.format("%s <a href='/auth/logout'>로그아웃</a>", loginUser.getName()));
    }
%>
</div>