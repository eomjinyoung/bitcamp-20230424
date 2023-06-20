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

    String command = null;

    while (true) {
      command = this.inputString(titleBuilder.toString());
      if (command.equals("history")) {
        this.printCommandHistory();
      } else if (command.equals("menu")) {
        this.printMenu();
      } else if (findMenuItem(command) == null) {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      } else {
        break;
      }
    }

    // 사용자가 입력한 명령어를 history에 보관
    if (this.commandHistory.size() == 10) {
      // 명령어 목록은 최대 10개만 유지한다.
      // 10개를 초과할 경우 맨앞의 기록을 삭제한다.
      this.commandHistory.poll();
    }
    String menuItem = findMenuItem(command);
    if (menuItem != null) {
      this.commandHistory.offer(titleBuilder.toString() + ": " + menuItem);
    } else {
      this.commandHistory.offer(command);
    }
    return command;
  }

  private String findMenuItem(String command) {
    String menuTitle = null;

    // command에 해당하는 메뉴가 있다면 그 메뉴 이름을 리턴하고
    // 없다면 null을 리턴한다.

    // 1) 현재 메뉴를 알아낸다. 메뉴 스택에서 맨 마지막에 입력한 메뉴를 조회한다.
    String menu = (String) this.menus.peek();

    // 2) 꺼낸 메뉴에서 해당 번호의 메뉴를 찾는다.
    String[] menuItems = menu.split("\n");
    for (String menuItem : menuItems) {
      if (menuItem.startsWith(command)) {
        return menuItem;
      }
    }

    return menuTitle;
  }
}








