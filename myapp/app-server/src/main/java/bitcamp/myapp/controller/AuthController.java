package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final MemberService memberService;

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
}
