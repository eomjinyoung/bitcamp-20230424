<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>

<%
    String email = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("email")) {
          email = cookie.getValue();
          break;
        }
      }
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>로그인</h1>

<form action='/auth/login.jsp' method='post'>
<table border='1'>
<tr>
  <th>이메일</th> <td><input type='email' name='email' value='<%=email%>'></td>
</tr>
<tr>
  <th>암호</th> <td><input type='password' name='password'></td>
</tr>
</table>
<button>로그인</button>
 <input type='checkbox' name='saveEmail'> 이메일 저장
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>












