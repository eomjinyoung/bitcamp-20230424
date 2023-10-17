package bitcamp.myapp.controller;

import bitcamp.myapp.security.MemberUserDetails;
import bitcamp.myapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final MemberService memberService;

  @GetMapping("/home")
  public String home(@AuthenticationPrincipal UserDetails principal, HttpSession session) throws Exception {
    System.out.println("=====================================> HomeController");
    System.out.println(principal);

    // 1) 파라미터로 받은 principal 객체가 Member 객체가 아닌 경우,
    //    MemberService 를 통해 Member 객체를 얻은 다음
    //    그 객체를 세션에 보관한다.
//    session.setAttribute("loginUser", memberService.get(principal.getUsername()));

    // 2) 파라미터로 받은 principal 객체가 Member 객체인 경우
    //    그 객체를 직접 세션에 보관한다.
//    session.setAttribute("loginUser", principal);

    // 3) principal 객체에 Member 객체가 들어 있는 경우
    ///   Member 객체를 꺼내서 세션에 보관한다.
    session.setAttribute("loginUser", ((MemberUserDetails) principal).getMember());

    return "index";
  }
}
