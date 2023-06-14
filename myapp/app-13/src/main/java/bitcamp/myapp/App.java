package bitcamp.myapp;

import bitcamp.myapp.handler.BoardHandler;
import bitcamp.myapp.handler.MemberHandler;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) {

    printTitle();

    printMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("99")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        MemberHandler.inputMember();
      } else if (menuNo.equals("2")) {
        MemberHandler.printMembers();
      } else if (menuNo.equals("3")) {
        MemberHandler.viewMember();
      } else if (menuNo.equals("4")) {
        MemberHandler.updateMember();
      } else if (menuNo.equals("5")) {
        MemberHandler.deleteMember();
      } else if (menuNo.equals("6")) {
        BoardHandler.inputBoard();
      } else if (menuNo.equals("7")) {
        BoardHandler.printBoards();
      } else if (menuNo.equals("8")) {
        BoardHandler.viewBoard();
      } else if (menuNo.equals("9")) {
        BoardHandler.updateBoard();
      } else if (menuNo.equals("10")) {
        BoardHandler.deleteBoard();
      }else {
        System.out.println(menuNo);
      }
    }

    Prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 회원등록");
    System.out.println("2. 회원목록");
    System.out.println("3. 회원조회");
    System.out.println("4. 회원변경");
    System.out.println("5. 회원삭제");
    System.out.println("6. 게시글등록");
    System.out.println("7. 게시글목록");
    System.out.println("8. 게시글조회");
    System.out.println("9. 게시글변경");
    System.out.println("10. 게시글삭제");
    System.out.println("99. 종료");
  }

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
  }
}
