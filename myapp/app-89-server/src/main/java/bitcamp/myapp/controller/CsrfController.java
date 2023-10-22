package bitcamp.myapp.controller;

import bitcamp.myapp.security.MemberUserDetails;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/csrf")
public class CsrfController {

  @RequestMapping("token")
  public RestResult token(HttpServletRequest request, HttpSession session) throws Exception {
    CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
    HashMap<String,String> data = new HashMap<>();
    data.put("token", csrfToken.getToken());
    data.put("sessionId", session.getId());

    if (csrfToken != null) {
      return RestResult.builder()
              .status(RestResult.SUCCESS)
              .data(data)
              .build();
    }

    return RestResult.builder()
            .status(RestResult.FAILURE)
            .error("XSRF-TOKEN 이 없습니다!")
            .build();
  }

}
