package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Member;

public interface MemberDao {
  void insert(Member member);
  List<Member> findAll();
  Member findBy(int no);
  Member findByEmailAndPassword(Member m);
  int update(Member member);
  int delete(int no);
}
