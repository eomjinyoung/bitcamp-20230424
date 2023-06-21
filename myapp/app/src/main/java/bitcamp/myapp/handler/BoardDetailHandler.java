package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.List;

public class BoardDetailHandler implements Handler {


  private List list;

  public BoardDetailHandler(List list) {
    this.list = list;
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











