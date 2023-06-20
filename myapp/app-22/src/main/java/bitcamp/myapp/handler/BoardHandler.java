package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.List;
import bitcamp.util.MenuPrompt;

public class BoardHandler implements Handler {


  private List list;
  private MenuPrompt prompt;

  private String title;

  public BoardHandler(MenuPrompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
  }

  public void execute() {

    prompt.appendBreadcrumb(this.title, getMenu());

    prompt.printMenu();

    while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0": prompt.removeBreadcrumb(); return;
        case "1": this.inputBoard(); break;
        case "2": this.printBoards(); break;
        case "3": this.viewBoard(); break;
        case "4": this.updateBoard(); break;
        case "5": this.deleteBoard(); break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 등록\n");
    menu.append("2. 목록\n");
    menu.append("3. 조회\n");
    menu.append("4. 변경\n");
    menu.append("5. 삭제\n");
    menu.append("0. 메인\n");
    return menu.toString();
  }

  private void inputBoard() {
    Board board = new Board();
    board.setTitle(this.prompt.inputString("제목? "));
    board.setContent(this.prompt.inputString("내용? "));
    board.setWriter(this.prompt.inputString("작성자? "));
    board.setPassword(this.prompt.inputString("암호? "));

    this.list.add(board);
  }

  private void printBoards() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Board board = (Board) this.list.get(i);
      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter(),
          board.getViewCount(),
          board.getCreatedDate());
    }
  }

  private void viewBoard() {
    int boardNo = this.prompt.inputInt("번호? ");

    Board board = this.findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("조회수: %s\n", board.getViewCount());
    System.out.printf("등록일: %tY-%1$tm-%1$td\n", board.getCreatedDate());
    board.setViewCount(board.getViewCount() + 1);
  }

  private void updateBoard() {
    int boardNo = this.prompt.inputInt("번호? ");

    Board board = this.findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    if (!this.prompt.inputString("암호? ").equals(board.getPassword())) {
      System.out.println("암호가 일치하지 않습니다!");
      return;
    }

    board.setTitle(this.prompt.inputString("제목(%s)? ", board.getTitle()));
    board.setContent(this.prompt.inputString("내용(%s)? ", board.getContent()));
  }

  private void deleteBoard() {
    if (!this.list.remove(new Board(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }

  private Board findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Board b = (Board) this.list.get(i);
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }


}











