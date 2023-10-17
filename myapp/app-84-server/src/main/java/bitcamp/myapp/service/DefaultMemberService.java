package bitcamp.myapp.service;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {

  @NonNull PasswordEncoder passwordEncoder;
  @NonNull MemberDao memberDao;

  @Transactional
  @Override
  public int add(Member member) {
    member.setPassword(passwordEncoder.encode(member.getPassword()));
    return memberDao.insert(member);
  }

  @Override
  public List<Member> list() {
    return memberDao.findAll();
  }

  @Override
  public Member get(int memberNo) {
    return memberDao.findBy(memberNo);
  }

  @Override
  public Member get(String email, String password) {
    return memberDao.findByEmailAndPassword(email, password);
  }

  @Override
  public Member get(String email) {
    return memberDao.findByEmail(email);
  }

  @Transactional
  @Override
  public int update(Member member) {
    member.setPassword(passwordEncoder.encode(member.getPassword()));
    return memberDao.update(member);
  }

  @Transactional
  @Override
  public int delete(int memberNo) {
    return memberDao.delete(memberNo);
  }
}
