package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler implements Handler {

  private ArrayList list = new ArrayList();
  private Prompt prompt;
  private String title;

  public MemberHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void execute() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
        return;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.inputMember();
      } else if (menuNo.equals("2")) {
        this.printMembers();
      } else if (menuNo.equals("3")) {
        this.viewMember();
      } else if (menuNo.equals("4")) {
        this.updateMember();
      } else if (menuNo.equals("5")) {
        this.deleteMember();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 메인");
  }

  private void inputMember() {
    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setEmail(this.prompt.inputString("이메일? "));
    m.setPassword(this.prompt.inputString("암호? "));
    m.setGender(inputGender((char)0));

    if (!this.list.add(m)) {
      System.out.println("입력 실패입니다!");
    }
  }

  private void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("---------------------------------------");

    Object[] arr = this.list.list();
    for (Object obj : arr) {
      Member m = (Member) obj;
      System.out.printf("%d, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(),
          toGenderString(m.getGender()));
    }
  }

  private void viewMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    Member m = (Member) this.list.get(new Member(memberNo));
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("이메일: %s\n", m.getEmail());
    System.out.printf("성별: %s\n", toGenderString(m.getGender()));
  }

  private static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  private void updateMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    Member m = (Member) this.list.get(new Member(memberNo));
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setName(this.prompt.inputString("이름(%s)? ", m.getName()));
    m.setEmail(this.prompt.inputString("이메일(%s)? ", m.getEmail()));
    m.setPassword(this.prompt.inputString("새암호? "));
    m.setGender(inputGender(m.getGender()));
  }

  private char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    while (true) {
      String menuNo = this.prompt.inputString(label +
          "  1. 남자\n" +
          "  2. 여자\n" +
          "> ");

      switch (menuNo) {
        case "1":
          return Member.MALE;
        case "2":
          return Member.FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private void deleteMember() {
    if (!this.list.delete(new Member(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }
}
