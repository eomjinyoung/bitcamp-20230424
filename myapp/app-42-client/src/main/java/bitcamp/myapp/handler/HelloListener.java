package bitcamp.myapp.handler;

import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class HelloListener implements ActionListener {
  @Override
  public void service(BreadcrumbPrompt prompt) {
    String name = prompt.inputString("이름은? ");
    System.out.printf("%s 님 반가워요!\n", name);
  }
}
