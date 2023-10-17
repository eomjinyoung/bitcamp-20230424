package bitcamp.myapp.security;

import org.springframework.security.crypto.password.PasswordEncoder;

// 테스트용 PasswordEncoder 구현체
// => DB에 저장된 암호 값을 사용자가 입력한 암호 값과 단순 비교한다.
public class SimplePasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    // 사용자가 입력한 암호를 DB에 저장하기 전에 특정 알고리즘으로 인코딩 할 때 호출한다.
    // 또는 사용자가 입력한 암호를 DB에 저장된 암호와 비교할 때 호출한다.
    // 왜? DB에 저장된 암호를 이 메서드가 리턴한 암호이기 때문이다.

    return rawPassword.toString();
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    // 로그인할 때 사용자가 입력한 암호와 DB에서 꺼낸 암호가 같은지 비교하기 위해서 호출한다.
    // rawPassword : 로그인 폼에 사용자가 입력한 문자열이다.
    // encodedPassword : UserDetailsService.loadUserByUsername() 메서드를 통해 DB에서 가져온 암호 문자열이다.
    return encodedPassword.equals(encode(rawPassword));
  }
}
