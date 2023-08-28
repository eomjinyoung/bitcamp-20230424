<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="bitcamp.myapp.vo.AttachedFile"%>
<%@ page import="bitcamp.myapp.vo.Board"%>

<jsp:useBean id="boardDao" type="bitcamp.myapp.dao.BoardDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="loginUser" class="bitcamp.myapp.vo.Member" scope="session"/>
<%
    if (loginUser.getNo() == 0) {
      response.sendRedirect("/auth/form.jsp");
      return;
    }

    int category = Integer.parseInt(request.getParameter("category"));
    int fileNo = Integer.parseInt(request.getParameter("no"));

    AttachedFile attachedFile = boardDao.findFileBy(fileNo);
    Board board = boardDao.findBy(category, attachedFile.getBoardNo());

    request.setAttribute("refresh", "2;url=detail.jsp?category=" + category
        + "&no=" + board.getNo());

    if (board.getWriter().getNo() != loginUser.getNo()) {
      throw new ServletException("게시글 변경 권한이 없습니다!");
    }


    if (boardDao.deleteFile(fileNo) == 0) {
        throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
    } else {
        response.sendRedirect("/board/detail.jsp?category=" + category + "&no=" + board.getNo());
    }
    sqlSessionFactory.openSession(false).commit();

%>











