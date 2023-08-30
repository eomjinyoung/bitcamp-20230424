package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/list")
public class MemberListController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    request.setAttribute("list", memberDao.findAll());
    request.setAttribute("viewUrl", "/WEB-INF/jsp/member/list.jsp");
  }

}
