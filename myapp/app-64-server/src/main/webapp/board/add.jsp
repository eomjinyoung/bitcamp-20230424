<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bitcamp.myapp.vo.AttachedFile"%>
<%@ page import="bitcamp.myapp.vo.Board"%>

<jsp:useBean id="boardDao" type="bitcamp.myapp.dao.BoardDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="bitcamp.util.NcpObjectStorageService" scope="application"/>
<jsp:useBean id="loginUser" class="bitcamp.myapp.vo.Member" scope="session"/>

<%
    if (loginUser.getNo() == 0) {
      response.sendRedirect("/auth/form.jsp");
      return;
    }

    // 오류가 발생했을 때 refresh 할 URL을 미리 지정한다.
    request.setAttribute("refresh", "2;url=list.jsp?category=" + request.getParameter("category"));

    Board board = new Board();
    board.setWriter(loginUser);
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));
    board.setCategory(Integer.parseInt(request.getParameter("category")));

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
    for (Part part : request.getParts()) {
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                  "bitcamp-nc7-bucket-118", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
    }
    board.setAttachedFiles(attachedFiles);

    boardDao.insert(board);
    if (attachedFiles.size() > 0) {
        boardDao.insertFiles(board);
    }

    sqlSessionFactory.openSession(false).commit();
    response.sendRedirect("list.jsp?category=" + request.getParameter("category"));
%>










