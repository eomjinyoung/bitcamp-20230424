package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/member/list")
public class MemberListController {

  @Autowired
  MemberService memberService;

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("list", memberService.list());
    return "/WEB-INF/jsp/member/list.jsp";
  }

}
