package bitcamp.util;

public class Menu {
  private String title;

  public Menu(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void execute(BreadcrumbPrompt prompt) {
    System.out.println(this.title + " 메뉴 실행!");
  }
}
