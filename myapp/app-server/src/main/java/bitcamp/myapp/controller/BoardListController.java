package bitcamp.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
      request.setAttribute("list", boardDao.findAll(Integer.parseInt(request.getParameter("category"))));

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/board/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("refresh", "1;url=/");
      throw new ServletException(e);
    }
  }

}











