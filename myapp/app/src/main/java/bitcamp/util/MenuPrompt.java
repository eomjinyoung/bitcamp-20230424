package bitcamp.util;

public class MenuPrompt extends Prompt {

  private Queue commandHistory = new Queue();
  private Stack menus = new Stack();
  private Stack breadcrumbs = new Stack();

  public void appendBreadcrumb(String title, String menu) {
    this.breadcrumbs.push(title);
    this.menus.push(menu);
  }

  public void removeBreadcrumb() {
    this.breadcrumbs.pop();
    this.menus.pop();
  }

  public void printMenu() {
    System.out.println(menus.peek());
  }

  public void printCommandHistory() {
    for (int i = 0; i < commandHistory.size(); i++) {
      System.out.println(commandHistory.get(i));
    }
  }

  public String inputMenu() {
    StringBuilder titleBuilder = new StringBuilder(); // 예) 메인/회원>
    for (int i = 0; i < this.breadcrumbs.size(); i++) {
      if (titleBuilder.length() > 0) {
        titleBuilder.append("/");
      }
      titleBuilder.append(this.breadcrumbs.get(i));
    }
    titleBuilder.append("> ");

    String command = this.inputString(titleBuilder.toString());

    if (command.equals("history")) {
      printCommandHistory();
      return "";
    }

    // 사용자가 입력한 명령어를 history에 보관
    if (commandHistory.size() == 10) {
      // 명령어 목록은 최대 10개만 유지한다.
      // 10개를 초과할 경우 맨앞의 기록을 삭제한다.
      commandHistory.poll();
    }
    commandHistory.offer(command);
    return command;
  }
}








