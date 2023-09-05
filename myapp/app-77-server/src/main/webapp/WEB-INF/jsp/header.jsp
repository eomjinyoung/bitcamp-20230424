<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style='height:50px;background-color:orange;'>
    <img src='https://www.ncloud.com/public/img/logo-m.png' style='height:40px'>
    <a href='/app/member/list'>회원</a>
    <a href='/app/board/list?category=1'>게시글</a>
    <a href='/app/board/list?category=2'>독서록</a>

<c:choose>
    <c:when test="${empty sessionScope.loginUser}">
        <a href='/app/auth/form'>로그인</a>
    </c:when>
    <c:otherwise>
        <c:if test="${empty sessionScope.loginUser.photo}">
            <img style='height:40px' src='/images/avatar.png'>
        </c:if>
        <c:if test="${not empty sessionScope.loginUser.photo}">
            <img src='http://mvsenqskbqzl19010704.cdn.ntruss.com/member/${loginUser.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
        </c:if>
        ${loginUser.name} <a href='/app/auth/logout'>로그아웃</a>
    </c:otherwise>
</c:choose>
</div>





