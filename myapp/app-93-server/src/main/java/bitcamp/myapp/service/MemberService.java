package bitcamp.myapp.service;

import bitcamp.myapp.vo.Member;

import java.util.List;

// 비즈니스 로직을 수행하는 객체의 사용 규칙 정의
// 메서드 이름은 업무와 관련된 이름을 사용할 것.
//
public interface MemberService {
  int add(Member member);
  List<Member> list();
  Member get(int memberNo);
  Member get(String email, String password);
  Member get(String email);
  int update(Member member);
  int delete(int memberNo);
}
