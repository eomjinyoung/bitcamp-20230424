package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDao {
  int insert(Member member);
  List<Member> findAll();
  Member findBy(int no);
  Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
  int update(Member member);
  int delete(int no);
}
