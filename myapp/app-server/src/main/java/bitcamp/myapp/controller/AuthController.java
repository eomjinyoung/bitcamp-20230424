package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final MemberService memberService;
  private final JwtDecoder decoder;

  @GetMapping("userInfo")
  public RestResult userInfo(Authentication authentication) throws Exception {
    Member loginUser = memberService.get(authentication.getName());
    if (loginUser == null) {
      return RestResult.builder()
              .status(RestResult.FAILURE)
              .error("로그아웃 상태입니다.")
              .build();
    }
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(loginUser)
            .build();
  }

  @GetMapping("logout")
  public RestResult logout(Authentication authentication) throws Exception {
    authentication.setAuthenticated(false);
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data("JWT 토큰을 무효화시켰습니다.")
            .build();
  }
}
