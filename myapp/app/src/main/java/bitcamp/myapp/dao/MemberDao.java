package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Member;

public interface MemberDao {
  void insert(Member member);
  List<Member> list();
}
