package bitcamp.myapp.controller;

import bitcamp.myapp.security.MemberUserDetails;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final MemberService memberService;

  @GetMapping("form")
  public RestResult form() throws Exception {
    return RestResult.builder()
            .status(RestResult.FAILURE)
            .error("로그인 하지 않았습니다!")
            .build();
  }

  @PostMapping("loginSuccess")
  public RestResult login(
          String email,
          String saveEmail,
          @AuthenticationPrincipal UserDetails principal,
          HttpSession session,
          HttpServletResponse response) throws Exception {

    // 응답 헤더에 이메일 쿠기를 추가한다.
    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      cookie.setPath("/");
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    session.setAttribute("loginUser", ((MemberUserDetails) principal).getMember());

    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .build();
  }

  @PostMapping("loginFailure")
  public RestResult loginFailure() throws Exception {
    return RestResult.builder()
            .status(RestResult.FAILURE)
            .build();
  }

  @GetMapping("userInfo")
  public RestResult userInfo(HttpSession session) {
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("로그아웃 상태입니다.")
              .build();
    }
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(session.getAttribute("loginUser"))
            .build();
  }
}
