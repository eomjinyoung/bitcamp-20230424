package bitcamp.myapp.security;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

  private final MemberService memberService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    System.out.println("====>" + email);
    Member member = memberService.get(email);
    if (member == null) {
      throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
    }
    return User.builder()
            .username(member.getEmail())
            .password(member.getPassword())
            .roles("USER")
            .build();
  }
}
