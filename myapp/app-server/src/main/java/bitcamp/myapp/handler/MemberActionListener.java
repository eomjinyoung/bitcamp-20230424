package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public interface MemberActionListener extends ActionListener {

  static char inputGender(char gender, BreadcrumbPrompt prompt) throws IOException {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", gender == 'M' ? "남성" : "여성");
    }

    while (true) {
      String menuNo = prompt.inputString(label +
          "  1. 남자\n" +
          "  2. 여자\n" +
          "> ");

      switch (menuNo) {
        case "1":
          return Member.MALE;
        case "2":
          return Member.FEMALE;
        default:
          prompt.println("무효한 번호입니다.");
      }
    }
  }

}
