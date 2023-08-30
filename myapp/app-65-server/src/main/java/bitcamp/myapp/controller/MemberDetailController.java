package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/detail")
public class MemberDetailController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    request.setAttribute("member", memberDao.findBy(Integer.parseInt(request.getParameter("no"))));
    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/WEB-INF/jsp/member/detail.jsp").include(request, response);
  }
}
