package bitcamp.myapp.controller;

import bitcamp.myapp.security.MemberUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jwt")
public class JwtController {

  @Autowired
  JwtEncoder encoder;

  @PostMapping("/token")
  public String token(
          Authentication authentication,
          @AuthenticationPrincipal MemberUserDetails userDetails) {

    Instant now = Instant.now();
    long expiry = 36000L;
    String scope = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));
    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiry))
            .subject(authentication.getName())
            .claims(map -> {
              // 사용자 권한 정보
              map.put("scope", scope);

              // 사용자 기타 정보
//              map.put("no", String.valueOf(userDetails.getMember().getNo()));
//              map.put("name", userDetails.getMember().getName());
//              map.put("email", userDetails.getMember().getEmail());
//              map.put("photo", userDetails.getMember().getPhoto());
            })
            .build();
    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

}
