package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/auth/form")
  public String form() {
    return "/WEB-INF/jsp/auth/form.jsp";
  }

  @RequestMapping("/auth/login")
  public String login(
          @RequestParam("email") String email,
          @RequestParam("password") String password,
          HttpSession session,
          HttpServletRequest request,
          HttpServletResponse response) throws Exception {

    if (request.getParameter("saveEmail") != null) {
      Cookie cookie = new Cookie("email", email);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member loginUser = memberService.get(email, password);
    if (loginUser == null) {
      request.setAttribute("refresh", "2;url=form");
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }

    session.setAttribute("loginUser", loginUser);
    return "redirect:/";
  }

  @RequestMapping("/auth/logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/";
  }
}
