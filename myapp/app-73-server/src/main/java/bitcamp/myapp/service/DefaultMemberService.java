package bitcamp.myapp.service;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Transactional;

import java.util.List;

//@Service
public class DefaultMemberService implements MemberService {

  MemberDao memberDao;

  public DefaultMemberService(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Transactional
  @Override
  public int add(Member member) throws Exception {
    return memberDao.insert(member);
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public Member get(int memberNo) throws Exception {
    return memberDao.findBy(memberNo);
  }

  @Override
  public Member get(String email, String password) throws Exception {
    return memberDao.findByEmailAndPassword(email, password);
  }

  @Transactional
  @Override
  public int update(Member member) throws Exception {
    return memberDao.update(member);
  }

  @Transactional
  @Override
  public int delete(int memberNo) throws Exception {
    return memberDao.delete(memberNo);
  }
}
