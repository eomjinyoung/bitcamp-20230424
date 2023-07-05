package bitcamp.myapp;

public class StudentSelector {
  public static void main(String[] args) throws Exception {
    String[] names = {
        "곽나현", "김동영", "김묘경", "김선래", "김선준", "김성주", "김세연", "김종원",
        "김현덕", "김호일", "박동우", "박승현", "박진혁", "방현석", "봉세환", "서재운",
        "손병진", "신준식", "신현우", "양소율", "오동현", "이승혁", "이정훈", "이진석",
        "전준호", "정연수", "주현욱", "최근우", "최기현", "한태영"
    };

    int count = (int) (Math.random() * 100 + 1);
    for (int i = 0; i < count; i++) {
      Math.random();
      System.out.print(".");
      Thread.sleep(50);
    }
    System.out.println();

    int selectedIndex = (int)(Math.random() * names.length);
    System.out.println("축 당첨!");
    Thread.sleep(3000);
    System.out.println(names[selectedIndex]);


  }
}
