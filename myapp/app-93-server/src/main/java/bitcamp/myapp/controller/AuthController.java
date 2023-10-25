package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private static final String DEFAULT_PASSWORD = "bitcamp-nopassword";

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

  @PostMapping("facebookLogin")
  public RestResult facebookLogin(@RequestBody String facebookAccessToken) throws Exception {

//    System.out.println(facebookAccessToken);

    // 클라이언트가 보낸 accessToken을 가지고
    // 페이스북 서버에 접속(AJAX 요청)하여 사용자를 정보를 가져온다.
    //
    RestTemplate restTemplate = new RestTemplate();

    @SuppressWarnings("rawtypes")
    Map result = restTemplate.getForObject(
            "https://graph.facebook.com/v18.0/me?access_token={value1}&fields={value2}", // 요청할 URL
            Map.class, // 서버에서 받은 결과의 타입
            facebookAccessToken, // URL의 첫 번째 자리에 들어갈 값
            "id,name,email,gender" // 페이스북 측에 요청하는 로그인 사용자 정보
    );

    // 페이스북에서 받은 데이터에서 이메일과 이름을 꺼낸다.
    @SuppressWarnings("null")
    String email = (String) result.get("email");
    String name = (String) result.get("name");

    // 기존 회원 정보 가져오기
    Member user = memberService.get(email);

    // 회원이 아니면 자동 등록하기
    if (user == null) {
      // 페이스북에서 받은 최소 정보를 가지고 회원 가입을 위한 객체를 준비한다.
      user = new Member();
      user.setEmail(email);
      user.setName(name);
      user.setPassword(DEFAULT_PASSWORD); // 임시 암호

      // 회원 가입을 수행한다.
      memberService.add(user);
    }

    // 등록된 회원 정보를 가지고 JWT를 요청
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.TEXT_PLAIN);
    headers.setBasicAuth(user.getEmail(), DEFAULT_PASSWORD);

    ResponseEntity<String> responseEntity = restTemplate.exchange(
            "http://localhost:8080/jwt/token", // 요청할 URL
            HttpMethod.POST,
            new HttpEntity<>(headers),
            String.class // 서버에서 받은 결과의 타입
    );

    // JWT 응답
    return RestResult.builder()
            .status(RestResult.SUCCESS)
            .data(responseEntity.getBody())
            .build();
  }
}
