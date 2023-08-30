package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/member/detail")
public class MemberDetailController implements PageController {

  MemberDao memberDao;

  public MemberDetailController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("member", memberDao.findBy(Integer.parseInt(request.getParameter("no"))));
    return "/WEB-INF/jsp/member/detail.jsp";
  }
}
