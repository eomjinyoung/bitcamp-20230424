package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class LoginListener implements MemberActionListener {

  MemberDao memberDao;

  public LoginListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    while (true) {
      Member m = new Member();
      m.setEmail(prompt.inputString("이메일? "));
      m.setPassword(prompt.inputString("암호? "));

      Member loginUser = memberDao.findByEmailAndPassword(m);
      if (loginUser == null) {
        prompt.println("회원 정보가 일치하지 않습니다.");
      } else {
        prompt.setAttribute("loginUser", loginUser);
        break;
      }
      prompt.end();
    }
  }
}
