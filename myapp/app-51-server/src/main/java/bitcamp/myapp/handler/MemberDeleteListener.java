package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class MemberDeleteListener implements ActionListener {

  MemberDao memberDao;
  DataSource ds;

  public MemberDeleteListener(MemberDao memberDao, DataSource ds) {
    this.memberDao = memberDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    try {
      if (memberDao.delete(prompt.inputInt("번호? ")) == 0) {
        prompt.println("해당 번호의 회원이 없습니다!");
      }
      prompt.println("삭제했습니다!");
      ds.getConnection().commit();

    } catch (Exception e) {
      try {ds.getConnection().rollback();} catch (Exception e2) {}
      throw new RuntimeException(e);
    }
  }

}
