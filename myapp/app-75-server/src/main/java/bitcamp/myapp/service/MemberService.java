package bitcamp.myapp.service;

import bitcamp.myapp.vo.Member;

import java.util.List;

// 비즈니스 로직을 수행하는 객체의 사용 규칙 정의
// 메서드 이름은 업무와 관련된 이름을 사용할 것.
//
public interface MemberService {
  int add(Member member) throws Exception;
  List<Member> list() throws Exception;
  Member get(int memberNo) throws Exception;
  Member get(String email, String password) throws Exception;
  int update(Member member) throws Exception;
  int delete(int memberNo) throws Exception;
}
