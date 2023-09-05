package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/member/delete")
public class MemberDeleteController {

  @Autowired
  MemberService memberService;

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    try {
      if (memberService.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } else {
        return "redirect:list";
      }

    } catch (Exception e) {
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }

}
