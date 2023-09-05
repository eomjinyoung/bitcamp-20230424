package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/member/detail")
public class MemberDetailController {

  @Autowired
  MemberService memberService;

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("member", memberService.get(Integer.parseInt(request.getParameter("no"))));
    return "/WEB-INF/jsp/member/detail.jsp";
  }
}
