<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="java.io.IOException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bitcamp.myapp.dao.BoardDao"%>
<%@ page import="bitcamp.myapp.vo.AttachedFile"%>
<%@ page import="bitcamp.myapp.vo.Board"%>
<%@ page import="bitcamp.myapp.vo.Member"%>
<%@ page import="bitcamp.util.NcpObjectStorageService"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>

<%
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    try {
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

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();

      // ErrorServlet 으로 포워딩 하기 전에 ErrorServlet이 사용할 데이터를
      // ServletRequest 보관소에 저장한다.
      request.setAttribute("error", e);
      request.setAttribute("message", "게시글 등록 오류!");
      request.setAttribute("refresh", "2;url=list?category=" + request.getParameter("category"));

      request.getRequestDispatcher("/error").forward(request, response);
    }
%>










