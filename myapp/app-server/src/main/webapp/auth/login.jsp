<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="refresh" value="2;url=/auth/form.jsp" scope="request"/>

<jsp:useBean id="m" class="bitcamp.myapp.vo.Member" scope="page"/>
<c:set target="${pageScope.m}" property="email" value="${param.email}"/>
<c:set target="${pageScope.m}" property="password" value="${param.password}"/>

<c:if test="${not empty param.saveEmail}">
  <%
    Cookie cookie = new Cookie("email", m.getEmail());
    response.addCookie(cookie);
  %>
</c:if>

<c:if test="${empty param.saveEmail}">
  <%
    Cookie cookie = new Cookie("email", "no");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  %>
</c:if>

<jsp:useBean id="memberDao" type="bitcamp.myapp.dao.MemberDao" scope="application"/>
<c:set var="loginUser" value="${memberDao.findByEmailAndPassword(m)}" scope="session"/>

<jsp:useBean id="loginUser" type="bitcamp.myapp.vo.Member" scope="session"/>
<c:redirect url="/"/>
