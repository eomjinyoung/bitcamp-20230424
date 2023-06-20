package bitcamp.util;

public class MenuPrompt extends Prompt {

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

  public String inputMenu() {
    StringBuilder titleBuilder = new StringBuilder(); // 예) 메인/회원>
    for (int i = 0; i < this.breadcrumbs.size(); i++) {
      if (titleBuilder.length() > 0) {
        titleBuilder.append("/");
      }
      titleBuilder.append(this.breadcrumbs.get(i));
    }
    titleBuilder.append("> ");

    return this.inputString(titleBuilder.toString());
  }
}








