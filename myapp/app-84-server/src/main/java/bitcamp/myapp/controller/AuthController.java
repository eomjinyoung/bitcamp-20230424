package bitcamp.myapp.controller;

import bitcamp.myapp.security.MemberUserDetails;
import bitcamp.myapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final MemberService memberService;

  @GetMapping("form")
  public void form(@CookieValue(required = false) String email, Model model) {
    model.addAttribute("email", email);
  }

  @PostMapping("loginSuccess")
  public String login(
          String email,
          String saveEmail,
          @AuthenticationPrincipal UserDetails principal,
          HttpSession session,
          HttpServletResponse response) throws Exception {
    // SpringSecurity의 인증 검사를 통과했을 때 수행할 작업

    // 응답 헤더에 이메일 쿠기를 추가한다.
    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    // 로그인 사용자의 정보를 세션에 보관한다.
    // 방법1) 파라미터로 받은 principal 객체가 Member 객체가 아닌 경우,
    //    MemberService 를 통해 Member 객체를 얻은 다음
    //    그 객체를 세션에 보관한다.
    //session.setAttribute("loginUser", memberService.get(principal.getUsername()));

    // 방법2) 파라미터로 받은 principal 객체가 Member 객체인 경우
    //    그 객체를 직접 세션에 보관한다.
    //session.setAttribute("loginUser", principal);

    // 방법3) principal 객체에 Member 객체가 들어 있는 경우
    //    Member 객체를 꺼내서 세션에 보관한다.
    session.setAttribute("loginUser", ((MemberUserDetails) principal).getMember());

    return "redirect:/home";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/";
  }
}
