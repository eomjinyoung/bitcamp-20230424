package bitcamp.myapp.security;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

// 로그인 요청이 들어 왔을 때
// username(예제에서는 email)에 해당하는 사용자 정보를 가져오는 일을 한다.
//
@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

  private final MemberService memberService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Member member = memberService.get(email);
    if (member == null) {
      throw new UsernameNotFoundException(email + " 사용자가 존재하지 않습니다.");
    }

    return User.builder()
            .username(member.getEmail())
            .password(member.getPassword())
            .roles("USER")
            .build();
  }
}
