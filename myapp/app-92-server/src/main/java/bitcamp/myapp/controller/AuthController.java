package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final MemberService memberService;
  private final JwtDecoder decoder;

  @GetMapping("userInfo")
  public RestResult userInfo(
          @RequestHeader("Authorization") String authorization,
          Authentication authentication) throws Exception {
    //1) JWT 에 사용자 정보를 보관한 경우
//    Jwt jwt = this.decoder.decode(authorization.split(" ")[1]);
//    Map<String,Object> claims = jwt.getClaims();
//    System.out.printf("no = %s\n", claims.get("no"));
//    System.out.printf("name = %s\n", claims.get("name"));
//    System.out.printf("email = %s\n", claims.get("email"));
//    System.out.printf("photo = %s\n", claims.get("photo"));

    //2) JWT 에 username(ID 또는 이메일 등)만 보관한 경우
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
    // JWT 을 무효화시키는 서버 측 코드를 둔다.
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data("JWT 토큰을 무효화시켰습니다.")
            .build();
  }
}
