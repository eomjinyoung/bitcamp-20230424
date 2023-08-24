<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%> <%-- directive element --%>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.myapp.dao.MemberDao"%>
<%@ page import="bitcamp.myapp.vo.Member"%>
<%@ page import="bitcamp.util.NcpObjectStorageService"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원 목록</h1>
<div style='margin:5px;'>
<a href='/member/form.jsp'>새 회원</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>이름</th> <th>이메일</th></tr>
</thead>

<%
    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");

    List<Member> list = memberDao.findAll();
    for (Member m : list) {
%>
    <tr>
        <td><%=m.getNo()%></td>
        <td>
            <img src='http://mvsenqskbqzl19010704.cdn.ntruss.com/member/<%=m.getPhoto()%>?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
            <a href='/member/detail.jsp?no=<%=m.getNo()%>'><%=m.getName()%></a></td>
        <td><%=m.getEmail()%></td>
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
