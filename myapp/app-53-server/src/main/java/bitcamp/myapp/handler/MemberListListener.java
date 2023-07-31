package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class MemberListListener implements ActionListener {

  MemberDao memberDao;

  public MemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("---------------------------------------");
    prompt.println("번호, 이름, 이메일");
    prompt.println("---------------------------------------");

    List<Member> list = memberDao.findAll();
    for (Member m : list) {
      prompt.printf("%d, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail());
    }
  }

}
